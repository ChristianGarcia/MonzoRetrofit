package com.christiangp.monzoapi.model.response;

import com.christiangp.monzoapi.model.Webhook;
import com.squareup.moshi.Json;

public final class RegisteredWebhookResponse {

    @Json(name = "webhook")
    private Webhook webhook;

    public final Webhook getWebhook() {
        return webhook;
    }
}
