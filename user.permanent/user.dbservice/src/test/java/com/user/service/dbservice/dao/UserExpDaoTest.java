//package com.user.service.dbservice.dao;
//
//import com.user.service.dbservice.model.UserExp;
//import com.user.service.dbservice.sessionmgr.DBUtils;
//import com.user.service.dbservice.sessionmgr.SqlSessionHolder;
//import com.user.view.BalanceOperation;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.List;
//import java.util.UUID;
//
//public class UserExpDaoTest {
//    private UserExpDao userExpDao;
//
//    @Before
//    public void setUp() throws Exception {
//        userExpDao = new UserExpDao(SqlSessionHolder.getSqlSession());
//        removeAll();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        removeAll();
//    }
//
//    private void removeAll() {
//        List<UserExp> userExps = userExpDao.queryAll();
//        userExps.forEach(userExpDao::delete);
//    }
//
//    private UserExp buildExp(String userID, double nowBalance, int op) {
//        return UserTestUtil.buildExp(userID, nowBalance, op);
//    }
//
//    @Test
//    public void create() {
//        String userID = UUID.randomUUID().toString();
//        double nowBalance = 1000;
//        UserExp exp = buildExp(userID, nowBalance, BalanceOperation.ADD);
//
//        int affectRow = userExpDao.create(exp);
//        Assert.assertEquals(1, affectRow);
//    }
//
//    @Test
//    public void queryByUser() {
//        String userID = UUID.randomUUID().toString();
//        double nowBalance = 1000;
//        UserExp exp = buildExp(userID, nowBalance, BalanceOperation.ADD);
//
//        int affectRow = userExpDao.create(exp);
//        exp = buildExp(userID, nowBalance, BalanceOperation.ADD);
//
//        affectRow = userExpDao.create(exp);
//
//        Assert.assertEquals(2, userExpDao.queryByUser(userID).size());
//    }
//
//    @Test
//    public void testQueryAll() {
//        String userID = UUID.randomUUID().toString();
//        double nowBalance = 1000;
//        UserExp exp = buildExp(userID, nowBalance, BalanceOperation.ADD);
//
//        int affectRow = userExpDao.create(exp);
//        Assert.assertEquals(affectRow, userExpDao.queryAll().size());
//    }
//
//    @Test
//    public void testDelete() {
//        String userID = UUID.randomUUID().toString();
//        double nowBalance = 1000;
//        UserExp exp = buildExp(userID, nowBalance, BalanceOperation.ADD);
//
//        userExpDao.create(exp);
//        int deleteRow = userExpDao.delete(exp);
//        Assert.assertEquals(1, deleteRow);
//    }
//}