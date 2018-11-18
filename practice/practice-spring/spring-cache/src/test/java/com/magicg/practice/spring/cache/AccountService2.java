package com.magicg.practice.spring.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService2 {


    // 使用了一个缓存名叫 accountCache
    @Cacheable(value = "accountCache", key = "#accountName")
    public Account getAccountByName(String accountName) {

        // 方法内部实现不考虑缓存逻辑，直接实现业务
        System.out.println("real querying account... " + accountName);
        Optional<Account> accountOptional = getFromDB(accountName);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }

        return accountOptional.get();
    }

    private Optional<Account> getFromDB(String accountName) {
        System.out.println("real querying db... " + accountName);
        //Todo query data from database
        return Optional.ofNullable(new Account(accountName));
    }

}
