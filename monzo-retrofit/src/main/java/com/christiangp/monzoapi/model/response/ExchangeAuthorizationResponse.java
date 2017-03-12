package com.christiangp.monzoapi.model.response;

import com.squareup.moshi.Json;

public final class ExchangeAuthorizationResponse {

    @Json(name = "access_token")
    private String accessToken;

    @Json(name = "client_id")
    private String clientId;

    @Json(name = "expires_in")
    private long   expiresIn;

    @Json(name = "refresh_token")
    private String refreshToken;

    @Json(name = "token_type")
    private String tokenType;

    @Json(name = "user_id")
    private String userId;

    public String getAccessToken() {
        return accessToken;
    }

    public String getClientId() {
        return clientId;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getUserId() {
        return userId;
    }
}
