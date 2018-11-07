package com.user.service.appservice.service;

import com.user.model.BalanceOperation;
import com.user.service.dbservice.domain.User;
import com.user.service.dbservice.domain.UserExp;

import java.sql.Date;
import java.util.UUID;

public class UserTestUtil {
    public static User buildUser() {
        User user = new User();
        user.setUserID(UUID.randomUUID().toString());
        user.setBalance(Math.random());
        user.setComment("");
        user.setPhoneNo("123");
        user.setRegDate(new Date(System.currentTimeMillis()));
        user.setUserName("UserName -" + System.currentTimeMillis());

        return user;
    }

    public static UserExp buildExp(String userID, double nowBalance, int operation) {
        UserExp userExp = new UserExp();
        userExp.setUserID(userID);
        userExp.setExpID(UUID.randomUUID().toString());
        userExp.setExpDate(new Date(System.currentTimeMillis()));
        userExp.setExpValue(Math.random() * 10);
        double newBalance = nowBalance + userExp.getExpValue();
        if (BalanceOperation.DE == operation) {
            newBalance = nowBalance - userExp.getExpValue();
        }
        userExp.setCurrentBalance(newBalance);
        userExp.setOperation(operation);

        return userExp;
    }
}
