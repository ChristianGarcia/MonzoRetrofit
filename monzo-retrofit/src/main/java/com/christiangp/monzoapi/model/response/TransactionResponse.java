package com.christiangp.monzoapi.model.response;

import com.christiangp.monzoapi.model.Transaction;
import com.squareup.moshi.Json;

public class TransactionResponse<T extends Transaction> {

    @Json(name = "transaction")
    private T transaction;

    public final T getTransaction() {
        return transaction;
    }
}
