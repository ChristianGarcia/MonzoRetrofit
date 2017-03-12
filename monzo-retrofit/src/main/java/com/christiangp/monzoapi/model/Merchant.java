package com.christiangp.monzoapi.model;

import com.squareup.moshi.Json;

public final class Merchant {

    @Json(name = "id")
    private String           id;

    @Json(name = "group_id")
    private String           groupId;

    @Json(name = "created")
    private String           createdDate;

    @Json(name = "name")
    private String           name;

    @Json(name = "logo")
    private String           logoUrl;

    @Json(name = "emoji")
    private String           emoji;

    @Json(name = "category")
    private String           category;

    @Json(name = "online")
    private boolean          online;

    @Json(name = "atm")
    private boolean          atm;

    @Json(name = "address")
    private Address          address;

    @Json(name = "updated")
    private String           updatedDate;

    @Json(name = "metadata")
    private MerchantMetadata metadata;

    @Json(name = "disable_feedback")
    private boolean          disableFeedback;

    public final String getId() {
        return id;
    }

    public final String getGroupId() {
        return groupId;
    }

    public final String getCreatedDate() {
        return createdDate;
    }

    public final String getName() {
        return name;
    }

    public final String getLogoUrl() {
        return logoUrl;
    }

    public final String getEmoji() {
        return emoji;
    }

    public final String getCategory() {
        return category;
    }

    public final boolean isOnline() {
        return online;
    }

    public final boolean isAtm() {
        return atm;
    }

    public final Address getAddress() {
        return address;
    }

    public final String getUpdatedDate() {
        return updatedDate;
    }

    public final MerchantMetadata getMetadata() {
        return metadata;
    }

    public final boolean isDisableFeedback() {
        return disableFeedback;
    }
}
