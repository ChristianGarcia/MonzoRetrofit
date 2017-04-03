package com.christiangp.monzoapi;

import com.christiangp.monzoapi.model.Attachment;
import com.christiangp.monzoapi.model.Balance;
import com.christiangp.monzoapi.model.TransactionWithMerchant;
import com.christiangp.monzoapi.model.TransactionWithMerchantId;
import com.christiangp.monzoapi.model.Webhook;
import com.christiangp.monzoapi.model.request.FeedItemMap;
import com.christiangp.monzoapi.model.request.MetadataMap;
import com.christiangp.monzoapi.model.request.PaginationOptions;
import com.christiangp.monzoapi.model.response.AccountsResponse;
import com.christiangp.monzoapi.model.response.WhoAmIResponse;

import org.junit.Ignore;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;

@Ignore // Don't run on CI
public class CallbackMonzoApiServiceIntegrationTestSuite
        extends MonzoApiServiceIntegrationTestSuite<MonzoApiService> {

    @Override
    protected MonzoApiService buildService(Retrofit retrofit) {
        return retrofit.create(MonzoApiService.class);
    }

    @Override
    protected WhoAmIResponse getWhoAmI(MonzoApiService apiService)
            throws IOException {
        return apiService.getWhoAmI()
                         .execute()
                         .body();
    }

    @Override
    protected AccountsResponse getAccounts(MonzoApiService apiService)
            throws IOException {
        return apiService.getAccounts()
                         .execute()
                         .body();
    }

    @Override
    protected Balance getBalance(MonzoApiService apiService, String testAccountId)
            throws IOException {
        return apiService.getBalance(testAccountId)
                         .execute()
                         .body();
    }

    @Override
    protected List<TransactionWithMerchantId> getTransactions(MonzoApiService apiService, String accountId, PaginationOptions pagination)
            throws IOException {
        return apiService.getTransactions(accountId, pagination)
                         .execute()
                         .body()
                         .getTransactions();
    }

    @Override
    protected List<TransactionWithMerchant> getTransactionsWithMerchant(MonzoApiService apiService, String testAccountId, PaginationOptions pagination)
            throws IOException {
        return apiService.getTransactionsWithMerchant(testAccountId, pagination)
                         .execute()
                         .body()
                         .getTransactions();
    }

    @Override
    protected TransactionWithMerchantId getTransaction(MonzoApiService apiService, String transactionId)
            throws IOException {
        return apiService.getTransaction(transactionId)
                         .execute()
                         .body()
                         .getTransaction();
    }

    @Override
    protected TransactionWithMerchant getTransactionWithMerchant(MonzoApiService apiService, String testTransactionId)
            throws IOException {
        return apiService.getTransactionWithMerchant(testTransactionId)
                         .execute()
                         .body()
                         .getTransaction();
    }

    @Override
    protected TransactionWithMerchantId addMetadataToTransaction(MonzoApiService apiService, String testTransactionId, MetadataMap metadata)
            throws IOException {
        return apiService.addMetadataToTransaction(testTransactionId, metadata)
                         .execute()
                         .body()
                         .getTransaction();
    }

    @Override
    protected void createFeedItem(FeedItemMap feedItemMap, MonzoApiService apiService, String testAccountId, String url)
            throws IOException {
        apiService.createFeedItem(testAccountId, url, feedItemMap)
                  .execute();
    }

    @Override
    protected List<Webhook> getWebhooks(MonzoApiService apiService, String testAccountId)
            throws IOException {
        return apiService.getWebhooks(testAccountId)
                         .execute()
                         .body()
                         .getWebhooks();
    }

    @Override
    protected Webhook registerWebhook(MonzoApiService apiService, String testAccountId, String webhookUrl)
            throws IOException {
        return apiService.registerWebhook(testAccountId, webhookUrl)
                         .execute()
                         .body()
                         .getWebhook();
    }

    @Override
    protected void unregisterWebhook(MonzoApiService apiService, String webhookId)
            throws IOException {
        apiService.unregisterWebhook(webhookId)
                  .execute();
    }

    @Override
    protected Attachment addAttachmentToTransaction(MonzoApiService apiService, String testTransactionId, String fileUrl, String fileType)
            throws IOException {
        return apiService.addAttachmentToTransaction(testTransactionId, fileUrl, fileType)
                         .execute()
                         .body()
                         .getAttachment();
    }

    @Override
    protected void removeAttachmentFromTransaction(MonzoApiService apiService, String attachmentId)
            throws IOException {
        apiService.removeAttachmentFromTransaction(attachmentId)
                  .execute();
    }
}
