package com.christiangp.monzoapi;

import com.christiangp.monzoapi.function.SimpleProvider;
import com.christiangp.monzoapi.model.Account;
import com.christiangp.monzoapi.model.Attachment;
import com.christiangp.monzoapi.model.Balance;
import com.christiangp.monzoapi.model.TransactionWithMerchant;
import com.christiangp.monzoapi.model.TransactionWithMerchantId;
import com.christiangp.monzoapi.model.Webhook;
import com.christiangp.monzoapi.model.request.BasicFeedItemMap;
import com.christiangp.monzoapi.model.request.FeedItemMap;
import com.christiangp.monzoapi.model.request.MetadataMap;
import com.christiangp.monzoapi.model.request.PaginationOptions;
import com.christiangp.monzoapi.model.response.AccountsResponse;
import com.christiangp.monzoapi.model.response.WhoAmIResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.assertj.core.api.Condition;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import static org.assertj.core.api.Assertions.assertThat;

abstract class MonzoApiServiceIntegrationTestSuite<S> {

    private S      apiService;

    private String testAccountId;

    private String testTransactionId;

    @Before
    public void setUp()
            throws Exception {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new AuthorizationInterceptor(new SimpleProvider<String>() {
                    @Override
                    public String provide() {
                        try {
                            return readPropertyFromKeys("access_token");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }))
                .build();
        final Retrofit retrofit = MonzoApi.retrofitBuilder()
                                          .client(client)
                                          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                          .build();
        apiService = buildService(retrofit);
        testAccountId = readPropertyFromKeys("account_id");
        testTransactionId = readPropertyFromKeys("tx_id");
    }

    @Test
    public void testWhoAmI()
            throws Exception {

        final WhoAmIResponse whoAmIResponse = getWhoAmI(apiService);

        assertThat(whoAmIResponse.getClientId()).isNotEmpty();
        assertThat(whoAmIResponse.getUserId()).isNotEmpty();
        assertThat(whoAmIResponse.isAuthenticated()).isTrue();
    }

    @Test
    public void testGetAccounts()
            throws Exception {

        final AccountsResponse accountsResponse = getAccounts(apiService);

        assertThat(accountsResponse.getAccounts()).isNotEmpty();
        final Account account = accountsResponse.getAccounts()
                                                .get(0);
        assertThat(account.getId()).isNotEmpty();
        assertThat(account.getDescription()).isNotEmpty();
        assertThat(account.getCreationDate()).isNotNull();
    }

    @Test
    public void testGetBalance()
            throws Exception {
        final Balance balance = getBalance(apiService, testAccountId);

        assertThat(balance.getCents()).isNotNull();
        assertThat(balance.getCurrencyCode()).isNotNull();
        assertThat(balance.getSpentTodayCents()).isNotNull();
    }

    @Test
    public void testGetTransactions()
            throws Exception {
        final ZonedDateTime startDate = ZonedDateTime.of(LocalDate.ofYearDay(2017, 20), LocalTime.MIDNIGHT, ZoneId.of("UTC"));
        final PaginationOptions pagination = new PaginationOptions.Builder().limitItemsTo(5)
                                                                            .after(startDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                                                                            .build();

        final List<TransactionWithMerchantId> transactions = getTransactions(apiService, testAccountId, pagination);
        assertThat(transactions).isNotEmpty();
        assertThat(transactions.get(0)
                               .getMerchantId()).isNotNull();
    }

    @Test
    public void testGetTransactions_WithMerchant()
            throws Exception {
        final ZonedDateTime startDate = ZonedDateTime.of(LocalDate.ofYearDay(2017, 20), LocalTime.MIDNIGHT, ZoneId.of("UTC"));
        final PaginationOptions pagination = new PaginationOptions.Builder().limitItemsTo(5)
                                                                            .after(startDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                                                                            .build();

        final List<TransactionWithMerchant> transactions = getTransactionsWithMerchant(apiService, testAccountId, pagination);
        assertThat(transactions).isNotEmpty();
        assertThat(transactions.get(0)
                               .getMerchant()).isNotNull();
    }

    @Test
    public void testGetTransaction()
            throws Exception {

        final TransactionWithMerchantId transaction = getTransaction(apiService, testTransactionId);
        assertThat(transaction.getMerchantId()).isNotEmpty();
    }

    @Test
    public void testGetTransaction_WithMerchant()
            throws Exception {

        final TransactionWithMerchant transaction = getTransactionWithMerchant(apiService, testTransactionId);
        assertThat(transaction.getMerchant()).isNotNull();
    }

    @Test
    public void testAddMetadataToTransaction()
            throws Exception {

        final MetadataMap metadata = new MetadataMap.Builder().add("testKey", "testValue")
                                                              .build();
        TransactionWithMerchantId modifiedTransaction = addMetadataToTransaction(apiService, testTransactionId, metadata);
        assertThat(modifiedTransaction.getMetadata()
                                      .get("testKey")).isEqualTo("testValue");
    }

    @Test
    public void testCreateFeedItem()
            throws Exception {

        final FeedItemMap feedItemMap = new BasicFeedItemMap.Builder("title", "image.png").build();
        createFeedItem(feedItemMap, apiService, testAccountId, "https://www.google.com");
    }

    @Test
    public void testRegisterListAndDeRegisterWebhook()
            throws Exception {

        final String webhookUrl = "https://127.0.0.1";

        //Register
        final Webhook registeredWebhook = registerWebhook(apiService, testAccountId, webhookUrl);

        assertThat(registeredWebhook.getAccountId()).isEqualTo(testAccountId);
        assertThat(registeredWebhook.getUrl()).isEqualTo(webhookUrl);
        assertThat(registeredWebhook.getId()).isNotNull();

        final String webhookId = registeredWebhook.getId();

        //List should now contain a webhook with the recently created webhook
        final List<Webhook> webhooks = getWebhooks(apiService, testAccountId);
        assertThat(webhooks).filteredOn(new Condition<Webhook>() {
            @Override
            public boolean matches(Webhook value) {
                return webhookId.equals(value.getId());
            }
        })
                            .hasSize(1); //One webhook with same id as created

        //Delete webhook
        unregisterWebhook(apiService, webhookId);

        // List should now contain NO webhooks with the initially registered id
        final List<Webhook> webhooksAfterDeletion = getWebhooks(apiService, testAccountId);
        assertThat(webhooksAfterDeletion).filteredOn(new Condition<Webhook>() {
            @Override
            public boolean matches(Webhook value) {
                return webhookId.equals(value.getId());
            }
        })
                                         .isEmpty(); //Has no webhooks with same id as created
    }

    @Test
    public void testAddAndRemoveAttachment()
            throws Exception {

        final Attachment attachment = addAttachmentToTransaction(apiService, testTransactionId, "http://www.nyan.cat/cats/original.gif", "image/gif");

        assertThat(attachment).isNotNull();

        removeAttachmentFromTransaction(apiService, attachment.getId());
    }

    protected abstract S buildService(Retrofit retrofit);

    protected abstract WhoAmIResponse getWhoAmI(S apiService)
            throws IOException;

    protected abstract AccountsResponse getAccounts(S apiService)
            throws IOException;

    protected abstract Balance getBalance(S apiService, String testAccountId)
            throws IOException;

    protected abstract List<TransactionWithMerchantId> getTransactions(S apiService, String accountId, PaginationOptions pagination)
            throws IOException;

    protected abstract List<TransactionWithMerchant> getTransactionsWithMerchant(
            S apiService,
            String testAccountId,
            PaginationOptions pagination
    )
            throws IOException;

    protected abstract TransactionWithMerchantId getTransaction(
            S apiService,
            String transactionId
    )
            throws IOException;

    protected abstract TransactionWithMerchant getTransactionWithMerchant(S apiService, String testTransactionId)
            throws IOException;

    protected abstract TransactionWithMerchantId addMetadataToTransaction(S apiService, String testTransactionId, MetadataMap metadata)
            throws IOException;

    protected abstract void createFeedItem(FeedItemMap feedItemMap, S apiService, String testAccountId, String url)
            throws IOException;

    protected abstract List<Webhook> getWebhooks(S apiService, String testAccountId)
            throws IOException;

    protected abstract Webhook registerWebhook(S apiService, String testAccountId, String webhookUrl)
            throws IOException;

    protected abstract void unregisterWebhook(S apiService, String webhookId)
            throws IOException;

    protected abstract Attachment addAttachmentToTransaction(S apiService, String testTransactionId, String fileUrl, String fileType)
            throws IOException;

    protected abstract void removeAttachmentFromTransaction(S apiService, String attachmentId)
            throws IOException;

    private String readProperty(String propertyKey, String propertiesFilePath)
            throws IOException {
        final Properties props = new Properties();
        final InputStream stream = new FileInputStream(propertiesFilePath);
        props.load(stream);
        return (String) props.get(propertyKey);
    }

    private String readPropertyFromKeys(String key)
            throws IOException {
        return readProperty(key, "test.properties");
    }
}
