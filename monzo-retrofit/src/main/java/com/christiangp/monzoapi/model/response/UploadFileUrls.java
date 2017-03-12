package com.christiangp.monzoapi.model.response;

import com.squareup.moshi.Json;

public final class UploadFileUrls {

    @Json(name = "file_url")
    private String fileUrl;

    @Json(name = "upload_url")
    private String uploadUrl;

    public final String getFileUrl() {
        return fileUrl;
    }

    public final String getUploadUrl() {
        return uploadUrl;
    }
}
