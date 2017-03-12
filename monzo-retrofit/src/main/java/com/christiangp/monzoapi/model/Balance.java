package com.christiangp.monzoapi.model;

import com.squareup.moshi.Json;

public final class Balance {

    @Json(name = "balance")
    private int    cents;

    @Json(name = "currency")
    private String currencyCode;

    @Json(name = "spend_today")
    private int    spentTodayCents;

    public final int getCents() {
        return cents;
    }

    public final String getCurrencyCode() {
        return currencyCode;
    }

    public final int getSpentTodayCents() {
        return spentTodayCents;
    }
}
