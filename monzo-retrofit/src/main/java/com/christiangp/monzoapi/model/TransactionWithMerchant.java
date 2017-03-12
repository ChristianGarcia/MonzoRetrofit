package com.christiangp.monzoapi.model;

import com.squareup.moshi.Json;

public final class TransactionWithMerchant
        extends Transaction {

    @Json(name = "merchant")
    private Merchant merchant;

    public Merchant getMerchant() {
        return merchant;
    }
}
