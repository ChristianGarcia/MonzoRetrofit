/*
 * Copyright (c) 2017 Christian García
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
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

import retrofit2.Call;
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

import static com.christiangp.monzoapi.MonzoApiHeaders.HEADER_NO_AUTHORIZATION;

/**
 * Retrofit interface for Monzo's API.
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
 *     .build();
 * </code></pre>
 * If you're providing your own interceptor, requests having a <code>No-Authentication: true</code>
 * header won't need an <code>Authorization</code> header
 *
 * @see <a href="https://monzo.com/docs/#authentication">https://monzo.com/docs/#authentication</a>
 * @see <a href="https://monzo.com/docs/">https://monzo.com/docs/</a>
 */
public interface MonzoApiService {

    /**
     * @see RxMonzoApiService#getAccessToken(String, String, String, String, String)
     */
    @Headers(HEADER_NO_AUTHORIZATION + ": true")
    @FormUrlEncoded
    @POST("/oauth2/token")
    Call<ExchangeAuthorizationResponse> getAccessToken(
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("redirect_uri") String redirectUri,
            @Field("code") String authorizationCode
    );

    /**
     * @see RxMonzoApiService#refreshToken(String, String, String, String)
     */
    @Headers(HEADER_NO_AUTHORIZATION + ": true")
    @FormUrlEncoded
    @POST("/oauth2/token")
    Call<ExchangeAuthorizationResponse> refreshAccessToken(
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("refresh_token") String refreshToken
    );

    /**
     * @see RxMonzoApiService#getWhoAmI()
     */
    @GET("/ping/whoami")
    Call<WhoAmIResponse> getWhoAmI();

    /**
     * @see RxMonzoApiService#getAccounts()
     */
    @GET("/accounts")
    Call<AccountsResponse> getAccounts();

    /**
     * @see RxMonzoApiService#getBalance(String)
     */
    @GET("/balance")
    Call<Balance> getBalance(
            @Query("account_id") String accountId
    );

    /**
     * @see RxMonzoApiService#getTransactions(String, PaginationOptions)
     */
    @GET("/transactions")
    Call<TransactionListResponse<TransactionWithMerchantId>> getTransactions(
            @Query("account_id") String accountId,
            @QueryMap PaginationOptions paginationOptions
    );

    /**
     * @see RxMonzoApiService#getTransactionsWithMerchant(String, PaginationOptions)
     */
    @GET("/transactions?expand[]=merchant")
    Call<TransactionListResponse<TransactionWithMerchant>> getTransactionsWithMerchant(
            @Query("account_id") String accountId,
            @QueryMap PaginationOptions paginationOptions
    );

    /**
     * @see RxMonzoApiService#getTransaction(String)
     */
    @GET("/transactions/{id}/")
    Call<TransactionResponse<TransactionWithMerchantId>> getTransaction(
            @Path("id") String transactionId
    );

    /**
     * @see RxMonzoApiService#getTransactionWithMerchant(String)
     */
    @GET("/transactions/{id}?expand[]=merchant")
    Call<TransactionResponse<TransactionWithMerchant>> getTransactionWithMerchant(
            @Path("id") String transactionId
    );

    /**
     * @see RxMonzoApiService#addMetadataToTransaction(String, MetadataMap)
     */
    @FormUrlEncoded
    @PATCH("/transactions/{id}")
    Call<TransactionResponse<TransactionWithMerchantId>> addMetadataToTransaction(
            @Path("id") String transactionId,
            @FieldMap MetadataMap metadataMap
    );

    /**
     * @see RxMonzoApiService#createFeedItem(String, String, FeedItemMap)
     */
    @FormUrlEncoded
    @POST("/feed")
    Call<Void> createFeedItem(
            @Field("account_id") String accountId,
            @Field("url") String url,
            @FieldMap FeedItemMap feedItemMap
    );

    /**
     * @see RxMonzoApiService#registerWebhook(String, String)
     */
    @FormUrlEncoded
    @POST("/webhooks")
    Call<RegisteredWebhookResponse> registerWebhook(
            @Field("account_id") String accountId,
            @Field("url") String webhookUrl
    );

    /**
     * @see RxMonzoApiService#getWebhooks(String)
     */
    @GET("/webhooks")
    Call<WebhooksResponse> getWebhooks(
            @Query("account_id") String accountId
    );

    /**
     * @see RxMonzoApiService#unregisterWebhook(String)
     */
    @DELETE("/webhooks/{id}")
    Call<Void> unregisterWebhook(
            @Path("id") String webhookId
    );

    /**
     * @see RxMonzoApiService#addAttachmentToTransaction(String, String, String)
     */
    @FormUrlEncoded
    @POST("/attachment/register")
    Call<AttachFileToTransactionResponse> addAttachmentToTransaction(
            @Field("external_id") String transactionId,
            @Field("file_url") String fileUrl,
            @Field("file_type") String fileType
    );

    /**
     * @see RxMonzoApiService#removeAttachmentFromTransaction(String)
     */
    @FormUrlEncoded
    @POST("/attachment/deregister")
    Call<Void> removeAttachmentFromTransaction(
            @Field("id") String attachmentId
    );
}
