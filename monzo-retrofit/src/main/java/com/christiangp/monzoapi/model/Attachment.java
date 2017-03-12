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
