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
