package com.christiangp.monzoapi.model.response;

import com.squareup.moshi.Json;

public final class WhoAmIResponse {

    @Json(name = "authenticated")
    private boolean authenticated;

    @Json(name = "client_id")
    private String  clientId;

    @Json(name = "user_id")
    private String  userId;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getClientId() {
        return clientId;
    }

    public String getUserId() {
        return userId;
    }
}
