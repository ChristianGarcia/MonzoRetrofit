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

import java.util.List;

import retrofit2.Retrofit;

@Ignore // Don't run on CI
public class RxMonzoApiServiceIntegrationTest
        extends MonzoApiServiceIntegrationTestSuite<RxMonzoApiService> {

    @Override
    protected RxMonzoApiService buildService(Retrofit retrofit) {
        return retrofit.create(RxMonzoApiService.class);
    }

    @Override
    protected WhoAmIResponse getWhoAmI(RxMonzoApiService apiService) {
        return apiService.getWhoAmI()
                         .test()
                         .assertNoErrors()
                         .values()
                         .get(0);
    }

    @Override
    protected AccountsResponse getAccounts(RxMonzoApiService apiService) {
        return apiService.getAccounts()
                         .test()
                         .assertNoErrors()
                         .values()
                         .get(0);
    }

    @Override
    protected Balance getBalance(RxMonzoApiService apiService, String testAccountId) {
        return apiService.getBalance(testAccountId)
                         .test()
                         .assertNoErrors()
                         .values()
                         .get(0);
    }

    @Override
    protected List<TransactionWithMerchantId> getTransactions(RxMonzoApiService apiService, String accountId, PaginationOptions pagination) {
        return apiService.getTransactions(accountId, pagination)
                         .test()
                         .assertNoErrors()
                         .values()
                         .get(0)
                         .getTransactions();
    }

    @Override
    protected List<TransactionWithMerchant> getTransactionsWithMerchant(RxMonzoApiService apiService, String testAccountId, PaginationOptions pagination) {
        return apiService.getTransactionsWithMerchant(testAccountId, pagination)
                         .test()
                         .assertNoErrors()
                         .values()
                         .get(0)
                         .getTransactions();
    }

    @Override
    protected TransactionWithMerchantId getTransaction(RxMonzoApiService apiService, String transactionId) {
        return apiService.getTransaction(transactionId)
                         .test()
                         .assertNoErrors()
                         .values()
                         .get(0)
                         .getTransaction();
    }

    @Override
    protected TransactionWithMerchant getTransactionWithMerchant(RxMonzoApiService apiService, String testTransactionId) {
        return apiService.getTransactionWithMerchant(testTransactionId)
                         .test()
                         .assertNoErrors()
                         .values()
                         .get(0)
                         .getTransaction();
    }

    @Override
    protected TransactionWithMerchantId addMetadataToTransaction(RxMonzoApiService apiService, String testTransactionId, MetadataMap metadata) {
        return apiService.addMetadataToTransaction(testTransactionId, metadata)
                         .test()
                         .assertNoErrors()
                         .values()
                         .get(0)
                         .getTransaction();
    }

    @Override
    protected void createFeedItem(FeedItemMap feedItemMap, RxMonzoApiService apiService, String testAccountId, String url) {
        apiService.createFeedItem(testAccountId, url, feedItemMap)
                  .test()
                  .assertNoErrors();
    }

    @Override
    protected List<Webhook> getWebhooks(RxMonzoApiService apiService, String testAccountId) {
        return apiService.getWebhooks(testAccountId)
                         .test()
                         .assertNoErrors()
                         .values()
                         .get(0)
                         .getWebhooks();
    }

    @Override
    protected Webhook registerWebhook(RxMonzoApiService apiService, String testAccountId, String webhookUrl) {
        return apiService.registerWebhook(testAccountId, webhookUrl)
                         .test()
                         .assertNoErrors()
                         .values()
                         .get(0)
                         .getWebhook();
    }

    @Override
    protected void unregisterWebhook(RxMonzoApiService apiService, String webhookId) {
        apiService.unregisterWebhook(webhookId)
                  .test()
                  .assertNoErrors();
    }

    @Override
    protected Attachment addAttachmentToTransaction(RxMonzoApiService apiService, String testTransactionId, String fileUrl, String fileType) {
        return apiService.addAttachmentToTransaction(testTransactionId, fileUrl, fileType)
                         .test()
                         .assertNoErrors()
                         .values()
                         .get(0)
                         .getAttachment();
    }

    @Override
    protected void removeAttachmentFromTransaction(RxMonzoApiService apiService, String attachmentId) {
        apiService.removeAttachmentFromTransaction(attachmentId)
                  .test()
                  .assertNoErrors();
    }
}