package com.christiangp.monzoapi;

import com.christiangp.monzoapi.model.Balance;
import com.christiangp.monzoapi.model.TransactionWithMerchant;
import com.christiangp.monzoapi.model.TransactionWithMerchantId;
import com.christiangp.monzoapi.model.request.FeedItemMap;
import com.christiangp.monzoapi.model.request.MetadataMap;
import com.christiangp.monzoapi.model.request.PaginationOptions;
import com.christiangp.monzoapi.model.response.AccountsResponse;
import com.christiangp.monzoapi.model.response.AttachFileToTransactionResponse;
import com.christiangp.monzoapi.model.response.ExchangeAuthorizationResponse;
import com.christiangp.monzoapi.model.response.RegisteredWebhookResponse;
import com.christiangp.monzoapi.model.response.TransactionListResponse;
import com.christiangp.monzoapi.model.response.TransactionResponse;
import com.christiangp.monzoapi.model.response.WebhooksResponse;
import com.christiangp.monzoapi.model.response.WhoAmIResponse;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * RxJava 2 Retrofit interface for Monzo's API.
 * <p>
 * Most methods require you to add an <a href="https://monzo.com/docs/#authentication">Authorization</a> header with the access token
 * for authorization (and authentication).
 * <p>
 * The provided {@link AuthorizationInterceptor} handles this for you. Simply add it to your Retrofit's
 * {@link okhttp3.OkHttpClient OkHttpClient} and provide it when creating a {@link MonzoApi}:
 * <pre><code>
 * OkHttpClient client = new OkHttpClient.Builder()
 *     .addInterceptor(new AuthorizationInterceptor(() -> accessToken))
 *     .build();
 *
 * Retrofit monzoRetrofit = MonzoApi.retrofitBuilder()
 *     .client(okHttpClient)
 *     .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
 *     .build();
 * </code></pre>
 * If you're providing your own interceptor, requests having a <code>No-Authentication: true</code>
 * header won't need an <code>Authorization</code> header
 *
 * @see <a href="https://monzo.com/docs/#authentication">https://monzo.com/docs/#authentication</a>
 * @see <a href="https://monzo.com/docs/">https://monzo.com/docs/</a>
 */
public interface RxMonzoApiService {

    String HEADER_NO_AUTHORIZATION = "No-Authentication";

    /**
     * Performs step #3 of
     * <a href="https://monzo.com/docs/#acquire-an-access-token">
     * Acquire an access token</a>.
     * <p>
     * See
     * <a href="https://monzo.com/docs/#exchange-the-authorization-code">
     * Exchange the authorization code</a>
     * for its params info
     * <p>
     * <b>IMPORTANT</b>. Since Android apps will need to store their secret somewhere accessible to their code,
     * your client secret should be of <b>non-confidential</b> type.
     * <p>
     * Non-confidential tokens expire quickly and their clients are not issued refresh tokens, so users will need to
     * constantly grant your client access to their accounts
     *
     * @see <a href="https://monzo.com/docs/#acquire-an-access-token">Acquire an access token</a>
     * @see <a href="https://monzo.com/docs/#exchange-the-authorization-code">Exchange the authorization code</a>
     */
    @Headers(HEADER_NO_AUTHORIZATION + ": true")
    @FormUrlEncoded
    @POST("/oauth2/token")
    Single<ExchangeAuthorizationResponse> exchangeAuthorizationCode(
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("redirect_uri") String redirectUri,
            @Field("code") String authorizationCode
    );

    /**
     * Basic API client info
     */
    @GET("/ping/whoami")
    Single<WhoAmIResponse> getWhoAmI();

    /**
     * Lists all accounts for the authenticated user.
     *
     * @see <a href="https://monzo.com/docs/#list-accounts">https://monzo.com/docs/#list-accounts</a>
     */
    @GET("/accounts")
    Single<AccountsResponse> getAccounts();

    /**
     * Loads the balance for the authenticated user.
     *
     * @see <a href="https://monzo.com/docs/#read-balance">https://monzo.com/docs/#read-balance</a>
     */
    @GET("/balance")
    Single<Balance> getBalance(
            @Query("account_id") String accountId
    );

    /**
     * Lists all transactions for the given account.
     *
     * @see <a href="https://monzo.com/docs/#list-transactions">https://monzo.com/docs/#list-transactions</a>
     */
    @GET("/transactions")
    Single<TransactionListResponse<TransactionWithMerchantId>> getTransactions(
            @Query("account_id") String accountId,
            @QueryMap PaginationOptions paginationOptions
    );

    /**
     * Lists all transactions for the given account and includes merchant info in each transaction.
     *
     * @see <a href="https://monzo.com/docs/#list-transactions">https://monzo.com/docs/#list-transactions</a>
     */
    @GET("/transactions?expand[]=merchant")
    Single<TransactionListResponse<TransactionWithMerchant>> getTransactionsWithMerchant(
            @Query("account_id") String accountId,
            @QueryMap PaginationOptions paginationOptions
    );

    /**
     * Loads the given transaction.
     *
     * @see <a href="https://monzo.com/docs/#retrieve-transaction">https://monzo.com/docs/#retrieve-transaction</a>
     */
    @GET("/transactions/{id}/")
    Single<TransactionResponse<TransactionWithMerchantId>> getTransaction(
            @Path("id") String transactionId
    );

    /**
     * Loads the given transaction and includes the merchant info.
     *
     * @see <a href="https://monzo.com/docs/#retrieve-transaction">https://monzo.com/docs/#retrieve-transaction</a>
     */
    @GET("/transactions/{id}?expand[]=merchant")
    Single<TransactionResponse<TransactionWithMerchant>> getTransactionWithMerchant(
            @Path("id") String transactionId
    );

    /**
     * Adds metadata to the given transaction.
     *
     * @see <a href="https://monzo.com/docs/#annotate-transaction">https://monzo.com/docs/#annotate-transaction</a>
     */
    @FormUrlEncoded
    @PATCH("/transactions/{id}")
    Single<TransactionResponse<TransactionWithMerchantId>> addMetadataToTransaction(
            @Path("id") String transactionId,
            @FieldMap MetadataMap metadataMap
    );

    /**
     * Creates a feed item.
     * <p>
     * To create the {@link FeedItemMap} instance, use any of its subclasses
     *
     * @see <a href="https://monzo.com/docs/#feed-items">https://monzo.com/docs/#feed-items</a>
     */
    @FormUrlEncoded
    @POST("/feed")
    Completable createFeedItem(
            @Field("account_id") String accountId,
            @Field("url") String url,
            @FieldMap FeedItemMap feedItemMap
    );

    /**
     * Registers a webhook.
     *
     * @see <a href="https://monzo.com/docs/#registering-a-webhook">https://monzo.com/docs/#registering-a-webhook</a>
     */
    @FormUrlEncoded
    @POST("/webhooks")
    Single<RegisteredWebhookResponse> registerWebhook(
            @Field("account_id") String accountId,
            @Field("url") String webhookUrl
    );

    /**
     * Lists all webhooks.
     *
     * @see <a href="https://monzo.com/docs/#list-webhooks">https://monzo.com/docs/#list-webhooks</a>
     */
    @GET("/webhooks")
    Single<WebhooksResponse> getWebhooks(
            @Query("account_id") String accountId
    );

    /**
     * Removes the given webhook
     *
     * @see <a href="https://monzo.com/docs/#deleting-a-webhook">https://monzo.com/docs/#deleting-a-webhook</a>
     */
    @DELETE("/webhooks/{id}")
    Completable unregisterWebhook(
            @Path("id") String webhookId
    );

    /**
     * Adds an attachment to a transaction.
     *
     * @see <a href="https://monzo.com/docs/#register-attachment">https://monzo.com/docs/#register-attachment</a>
     */
    @FormUrlEncoded
    @POST("/attachment/register")
    Single<AttachFileToTransactionResponse> addAttachmentToTransaction(
            @Field("external_id") String transactionId,
            @Field("file_url") String fileUrl,
            @Field("file_type") String fileType
    );

    /**
     * Removes the given attachment from the transaction its attached to
     *
     * @see <a href="https://monzo.com/docs/#deregister-attachment">https://monzo.com/docs/#deregister-attachment</a>
     */
    @FormUrlEncoded
    @POST("/attachment/deregister")
    Completable removeAttachmentFromTransaction(
            @Field("id") String attachmentId
    );
}
