package com.christiangp.monzoapi.model.response;

import com.christiangp.monzoapi.model.Transaction;
import com.squareup.moshi.Json;

import java.util.List;

public class TransactionListResponse<T extends Transaction> {

    @Json(name = "transactions")
    private List<T> transactions;

    public final List<T> getTransactions() {
        return transactions;
    }

}
