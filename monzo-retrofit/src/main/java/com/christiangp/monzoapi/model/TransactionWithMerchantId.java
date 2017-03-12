package com.christiangp.monzoapi.model;

import com.squareup.moshi.Json;

public final class TransactionWithMerchantId extends Transaction {

    @Json(name = "merchant")
    private String merchantId;

    public final String getMerchantId() {
        return merchantId;
    }
}
