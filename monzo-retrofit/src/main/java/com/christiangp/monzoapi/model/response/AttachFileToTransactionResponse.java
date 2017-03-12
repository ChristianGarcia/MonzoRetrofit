package com.christiangp.monzoapi.model.response;

import com.christiangp.monzoapi.model.Attachment;
import com.squareup.moshi.Json;

public final class AttachFileToTransactionResponse {

    @Json(name = "attachment")
    private Attachment attachment;

    public final Attachment getAttachment() {
        return attachment;
    }
}
