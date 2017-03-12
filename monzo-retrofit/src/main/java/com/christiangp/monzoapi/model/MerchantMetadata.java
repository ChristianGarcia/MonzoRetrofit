/*
 * Copyright (c) 2017 Christian García
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

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
