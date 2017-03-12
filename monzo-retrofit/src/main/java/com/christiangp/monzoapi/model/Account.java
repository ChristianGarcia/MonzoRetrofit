package com.christiangp.monzoapi.model;

import com.squareup.moshi.Json;

public final class Account {

    @Json(name = "id")
    private String        id;

    @Json(name = "description")
    private String        description;

    @Json(name = "created")
    private String creationDate;

    public final String getId() {
        return id;
    }

    public final String getDescription() {
        return description;
    }

    public final String getCreationDate() {
        return creationDate;
    }
}
