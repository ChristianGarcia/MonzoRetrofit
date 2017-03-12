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

public final class Address {

    @Json(name = "short_formatted")
    private String  shortFormatted;

    @Json(name = "formatted")
    private String  formatted;

    @Json(name = "address")
    private String  address;

    @Json(name = "city")
    private String  city;

    @Json(name = "region")
    private String  region;

    @Json(name = "country")
    private String  country;

    @Json(name = "postcode")
    private String  postcode;

    @Json(name = "latitude")
    private double  latitude;

    @Json(name = "longitude")
    private double  longitude;

    @Json(name = "zoom_level")
    private int     zoomLevel;

    @Json(name = "approximate")
    private boolean approximate;

    public final String getShortFormatted() {
        return shortFormatted;
    }

    public final String getFormatted() {
        return formatted;
    }

    public final String getAddress() {
        return address;
    }

    public final String getCity() {
        return city;
    }

    public final String getRegion() {
        return region;
    }

    public final String getCountry() {
        return country;
    }

    public final String getPostcode() {
        return postcode;
    }

    public final double getLatitude() {
        return latitude;
    }

    public final double getLongitude() {
        return longitude;
    }

    public final int getZoomLevel() {
        return zoomLevel;
    }

    public final boolean isApproximate() {
        return approximate;
    }
}
