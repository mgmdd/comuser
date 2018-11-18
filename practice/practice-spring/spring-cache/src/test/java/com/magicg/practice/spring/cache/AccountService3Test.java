package com.magicg.practice.spring.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = "classpath*:spring/*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountService3Test {

    @Autowired
    private AccountService3 accountService3;

    @Test
    public void testGetAccountByName() {

        System.out.println("first query.....");
        accountService3.getAccountByName("accountName");

        System.out.println("second query....");
        accountService3.getAccountByName("accountName");

    }

    @Test
    public void testUpdateAccount() {
        Account account1 = accountService3.getAccountByName("accountName1");
        System.out.println(account1.toString());
        Account account2 = accountService3.getAccountByName("accountName2");
        System.out.println(account2.toString());

        account2.setId(121212);
        accountService3.updateAccount(account2);

        // account1会走缓存
        account1 = accountService3.getAccountByName("accountName1");
        System.out.println(account1.toString());
        // account2会查询db
        account2 = accountService3.getAccountByName("accountName2");
        System.out.println(account2.toString());

    }

    @Test
    public void testReload() {
        accountService3.reload();
        // 这2行查询数据库
        accountService3.getAccountByName("somebody1");
        accountService3.getAccountByName("somebody2");

        // 这两行走缓存
        accountService3.getAccountByName("somebody1");
        accountService3.getAccountByName("somebody2");
    }
}