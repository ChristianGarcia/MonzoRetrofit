package com.christiangp.monzoapi.model;

import com.squareup.moshi.Json;

import java.util.Map;

public abstract class Transaction {

    @Json(name = "id")
    private String              id;

    @Json(name = "created")
    private String              createdDate;

    @Json(name = "description")
    private String              description;

    @Json(name = "amount")
    private int                 amountCents;

    @Json(name = "currency")
    private String              currencyCode;

    @Json(name = "notes")
    private String              notes;

    @Json(name = "metadata")
    private Map<String, String> metadata;

    @Json(name = "account_balance")
    private int                 accountBalanceCents;

    //    @Json(name = "attachments")

    @Json(name = "category")
    private String              category;

    @Json(name = "is_load")
    private boolean             load;

    @Json(name = "settled")
    private String              settledDate;

    @Json(name = "local_amount")
    private int                 localAmountCents;

    @Json(name = "local_currency")
    private String              localCurrencyCode;

    @Json(name = "updated")
    private String              updatedDate;

    @Json(name = "account_id")
    private String              accountId;

    @Json(name = "counterparty")
    private Map<String, String> counterParty;

    @Json(name = "scheme")
    private String              scheme;

    @Json(name = "dedupe_id")
    private String              dedupeId;

    @Json(name = "originator")
    private boolean             originator;

    @Json(name = "include_in_spending")
    private boolean             includeInSpending;

    @Json(name = "decline_reason")
    private String              declineReason;

    public final String getId() {
        return id;
    }

    public final int getAmountCents() {
        return amountCents;
    }

    public final String getCurrencyCode() {
        return currencyCode;
    }

    public final String getNotes() {
        return notes;
    }

    public final Map<String, String> getMetadata() {
        return metadata;
    }

    public final int getAccountBalanceCents() {
        return accountBalanceCents;
    }

    public final String getCategory() {
        return category;
    }

    public final boolean isLoad() {
        return load;
    }

    public final String getSettledDate() {
        return settledDate;
    }

    public final int getLocalAmountCents() {
        return localAmountCents;
    }

    public final String getLocalCurrencyCode() {
        return localCurrencyCode;
    }

    public final String getUpdatedDate() {
        return updatedDate;
    }

    public final String getAccountId() {
        return accountId;
    }

    public final Map<String, String> getCounterParty() {
        return counterParty;
    }

    public final String getScheme() {
        return scheme;
    }

    public final String getDedupeId() {
        return dedupeId;
    }

    public final boolean isOriginator() {
        return originator;
    }

    public final boolean isIncludeInSpending() {
        return includeInSpending;
    }

    public final String getDeclineReason() {
        return declineReason;
    }
}
