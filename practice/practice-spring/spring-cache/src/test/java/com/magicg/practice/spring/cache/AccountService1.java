package com.magicg.practice.spring.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "accountService1")
public class AccountService1 {

    @Autowired
    private CacheContext<Account> accountCacheContext;

    public Account getAccountByName(String accountName) {
        Account result = accountCacheContext.get(accountName);
        if (result != null) {
            System.out.println("get from cache... {}" + accountName);
            return result;
        }

        Optional<Account> accountOptional = getFromDB(accountName);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }
        Account account = accountOptional.get();
        accountCacheContext.addOrUpdateCache(accountName, account);
        return account;
    }

    public void reload() {
        accountCacheContext.evictCache();
    }

    private Optional<Account> getFromDB(String accountName) {
        System.out.println("real querying db... {}" + accountName);
        //Todo query data from database
        return Optional.ofNullable(new Account(accountName));
    }

}