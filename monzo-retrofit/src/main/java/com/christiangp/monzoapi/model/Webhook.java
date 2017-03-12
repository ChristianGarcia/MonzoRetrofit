package com.christiangp.monzoapi.model;

import com.squareup.moshi.Json;

public final class Webhook {

    @Json(name = "id")
    private String id;

    @Json(name = "account_id")
    private String accountId;

    @Json(name = "url")
    private String url;

    public final String getId() {
        return id;
    }

    public final String getAccountId() {
        return accountId;
    }

    public final String getUrl() {
        return url;
    }
}
