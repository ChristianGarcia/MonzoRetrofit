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
