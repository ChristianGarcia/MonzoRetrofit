package com.christiangp.monzoapi.model;

import com.squareup.moshi.Json;

public final class MerchantMetadata {

    @Json(name = "created_for_merchant")
    private String createdForMerchantId;

    @Json(name = "created_for_transaction")
    private String createdForTransactionId;

    @Json(name = "enriched_from_settlement")
    private String enrichedFromSettlementId;

    @Json(name = "foursquare_category")
    private String foursquareCategory;

    @Json(name = "foursquare_id")
    private String foursquareId;

    @Json(name = "foursquare_website")
    private String foursquareWebsite;

    @Json(name = "google_places_icon")
    private String googlePlacesIcon;

    @Json(name = "google_places_id")
    private String googlePlacesId;

    @Json(name = "google_places_name")
    private String googlePlacesName;

    @Json(name = "provider")
    private String provider;

    @Json(name = "provider_id")
    private String providerId;

    @Json(name = "suggested_name")
    private String suggestedName;

    @Json(name = "suggested_tags")
    private String suggestedTags;

    @Json(name = "twitter_id")
    private String twitterId;

    @Json(name = "website")
    private String website;
}
