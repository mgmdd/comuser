package com.user.model.user;

import java.sql.Date;

public class UserExp {
    private String expID;
    private String userID;
    private Date expDate;
    private double expValue;
    private int operation;
    private double currentBalance;

    public UserExp() {
    }

    public String getExpID() {
        return expID;
    }

    public void setExpID(String expID) {
        this.expID = expID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public double getExpValue() {
        return expValue;
    }

    public void setExpValue(double expValue) {
        this.expValue = expValue;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
}
