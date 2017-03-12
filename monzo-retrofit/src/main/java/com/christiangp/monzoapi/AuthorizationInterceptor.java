package com.christiangp.monzoapi;

import com.christiangp.monzoapi.function.SimpleProvider;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class AuthorizationInterceptor
        implements Interceptor {

    private final SimpleProvider<String> provider;

    public AuthorizationInterceptor(SimpleProvider<String> provider) {
        this.provider = provider;
    }

    @Override
    public Response intercept(Chain chain)
            throws IOException {
        final Request request = chain.request();
        if (request.header(RxMonzoApiService.HEADER_NO_AUTHORIZATION) == null) {
            // Authorization header required
            return chain.proceed(request.newBuilder()
                                        .addHeader("Authorization", "Bearer " + provider.provide())
                                        .build());
        }
        return chain.proceed(request.newBuilder()
                                    .removeHeader(RxMonzoApiService.HEADER_NO_AUTHORIZATION)
                                    .build());
    }
}
