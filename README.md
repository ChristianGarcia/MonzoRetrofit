# MonzoRetrofit
[![Build Status](https://travis-ci.org/ChristianGarcia/MonzoRetrofit.svg?branch=master)](https://travis-ci.org/ChristianGarcia/MonzoRetrofit)
[![](https://img.shields.io/maven-central/v/com.christiangp/monzo-retrofit.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.christiangp%22%20a%3A%22monzo-retrofit%22)

A Retrofit interface for [Monzo's public API](https://monzo.com/docs/).

## Binaries
```groovy
dependencies {
    compile 'com.christiangp:monzo-retrofit:<latest_version>'
}
```

## Usage
Create a `RxMonzoApiService` through `MonzoApi.retrofitBuilder()`.

Most methods require you to add an [Authorization](https://monzo.com/docs/#authentication) header with an access token
for authorization (and authentication). The provided `AuthorizationInterceptor` handles this for you. Simply add it to your Retrofit's `OkHttpClient` and provide it when creating the `Retrofit` instance.

```java
OkHttpClient client = new OkHttpClient.Builder()
    .addInterceptor(new AuthorizationInterceptor(() -> accessToken))
    .build();

Retrofit monzoRetrofit = MonzoApi.retrofitBuilder()
    .client(okHttpClient)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
    .build();
```

If you don't want to use `AuthorizationInterceptor`, requests having a `No-Authentication: true`
header won't need an `Authorization` header. All other requests require it.

## License

    Copyright 2017 Christian García

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
