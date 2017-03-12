package com.christiangp.monzoapi.model.response;

import com.christiangp.monzoapi.model.Webhook;
import com.squareup.moshi.Json;

import java.util.List;

public final class WebhooksResponse {

    @Json(name = "webhooks")
    private List<Webhook> webhooks;

    public final List<Webhook> getWebhooks() {
        return webhooks;
    }
}
