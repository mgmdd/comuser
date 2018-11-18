package com.magicg.practice.spring.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@ContextConfiguration(locations = "classpath*:spring/*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountService2Test {

    @Autowired
    private AccountService2 accountService2;


    @Test
    public void testInject() {
        assertNotNull(accountService2);
    }

    @Test
    public void testGetAccountByName() {
        System.out.println("first query...");
        accountService2.getAccountByName("accountName");

        System.out.println("second query...");
        accountService2.getAccountByName("accountName");

        System.out.println("first query...");
        accountService2.getAccountByName("accountName2");

        System.out.println("second query...");
        accountService2.getAccountByName("accountName2");
    }
}