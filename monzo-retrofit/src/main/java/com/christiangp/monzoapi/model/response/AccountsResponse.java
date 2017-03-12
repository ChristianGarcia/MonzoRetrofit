package com.christiangp.monzoapi.model.response;

import com.christiangp.monzoapi.model.Account;
import com.squareup.moshi.Json;

import java.util.List;

public final class AccountsResponse {

    @Json(name = "accounts")
    private List<Account> accounts;

    public final List<Account> getAccounts() {
        return accounts;
    }
}
