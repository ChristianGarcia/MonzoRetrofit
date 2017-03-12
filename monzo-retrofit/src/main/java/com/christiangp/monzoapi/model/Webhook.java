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

public final class Webhook {

    @Json(name = "id")
    private String id;

    @Json(name = "account_id")
    private String accountId;

    @Json(name = "url")
    private String url;

    public final String getId() {
        return id;
    }

    public final String getAccountId() {
        return accountId;
    }

    public final String getUrl() {
        return url;
    }
}
