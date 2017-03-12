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

public final class Balance {

    @Json(name = "balance")
    private int    cents;

    @Json(name = "currency")
    private String currencyCode;

    @Json(name = "spend_today")
    private int    spentTodayCents;

    public final int getCents() {
        return cents;
    }

    public final String getCurrencyCode() {
        return currencyCode;
    }

    public final int getSpentTodayCents() {
        return spentTodayCents;
    }
}
