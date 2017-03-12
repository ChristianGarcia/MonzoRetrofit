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

package com.christiangp.monzoapi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Create a Retrofit Builder with the minimum set up this library needs.
 * <p>
 * This will automatically add Monzo's base URL and a {@link MoshiConverterFactory} needed to serialize the library's models
 */
public final class MonzoApi {

    private MonzoApi() {

    }

    public static Retrofit.Builder retrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl("https://api.monzo.com")
                .addConverterFactory(MoshiConverterFactory.create());
    }
}
