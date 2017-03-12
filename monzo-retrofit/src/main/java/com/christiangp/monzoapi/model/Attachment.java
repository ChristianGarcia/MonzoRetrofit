package com.christiangp.monzoapi.model;

import com.squareup.moshi.Json;

public final class Attachment {

    @Json(name = "id")
    private String id;

    @Json(name = "user_id")
    private String userId;

    @Json(name = "external_id")
    private String transactionId;

    @Json(name = "file_url")
    private String fileUrl;

    @Json(name = "file_type")
    private String fileType;

    @Json(name = "created")
    private String createdDate;

    public final String getId() {
        return id;
    }

    public final String getUserId() {
        return userId;
    }

    public final String getTransactionId() {
        return transactionId;
    }

    public final String getFileUrl() {
        return fileUrl;
    }

    public final String getFileType() {
        return fileType;
    }

    public final String getCreatedDate() {
        return createdDate;
    }
}
