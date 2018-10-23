//package com.user.service.dbservice.dao;
//
//import com.user.service.dbservice.model.User;
//import com.user.service.dbservice.model.UserCondition;
//import com.user.service.dbservice.sessionmgr.DBUtils;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoTest {
//
//    private UserDao userDao;
//
//    @Before
//    public void setUp() throws Exception {
//        userDao = new UserDao(DBUtils.getSqlSession());
//        removeAll();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        removeAll();
//    }
//
//    private void removeAll() {
//        userDao.queryAll().forEach(userDao::deleteUser);
//    }
//
//    private User buildUser() {
//        return UserTestUtil.buildUser();
//    }
//
//    @Test
//    public void deleteUser() {
//        User user = buildUser();
//        int createCount = userDao.createUser(user);
//        Assert.assertTrue(createCount == 1);
//
//        int deleteCount = userDao.deleteUser(user);
//        Assert.assertTrue(deleteCount == 1);
//
//        user = buildUser();
//        deleteCount = userDao.deleteUser(user);
//
//        Assert.assertTrue(deleteCount == 0);
//    }
//
//    @Test
//    public void createUser() {
//        User user = buildUser();
//
//        int createCount = userDao.createUser(user);
//        Assert.assertTrue(createCount == 1);
//
//        User userindb = userDao.queryByID(user);
//        Assert.assertNotNull(userindb);
//
//        assertUserSame(user, userindb);
//
//        boolean exp = false;
//        try {
//            createCount = userDao.createUser(userindb);
//        } catch (Exception e) {
//            exp = true;
//        }
//        Assert.assertTrue(exp);
//
//    }
//
//    public void assertUserSame(User exp, User target) {
//        Assert.assertEquals(exp.getUserID(), target.getUserID());
//        Assert.assertEquals(exp.getUserName(), target.getUserName());
//        Assert.assertEquals(exp.getPhoneNo(), target.getPhoneNo());
//        Assert.assertEquals(Double.valueOf(exp.getBalance()), Double.valueOf(target.getBalance()));
//        Assert.assertEquals(exp.getRegDate(), target.getRegDate());
//        Assert.assertEquals(exp.getComment(), target.getComment());
//    }
//
//    @Test
//    public void queryAllEmpty() {
//
//        List<User> users = userDao.queryAll();
//        Assert.assertTrue(users.isEmpty());
//
//
//    }
//
//    @Test
//    public void queryAll() {
//
//        //add 3
//        User u1 = buildUser();
//        int createCount = userDao.createUser(u1);
//        Assert.assertTrue(createCount == 1);
//
//        User u2 = buildUser();
//        createCount = userDao.createUser(u2);
//        Assert.assertTrue(createCount == 1);
//
//        User u3 = buildUser();
//        createCount = userDao.createUser(u3);
//        Assert.assertTrue(createCount == 1);
//
//        //get all
//        //compare
//    }
//
//    @Test
//    public void queryByCondition() {
//        String constren = "conditon";
//        String constrzh = "叶问";
//        List<User> users = new ArrayList<>(10);
//        for (int i = 0; i < 10; i++) {
//            User user = buildUser();
//            if (i < 5) {
//                user.setUserName(constren + i);
//            } else {
//                user.setUserName(constrzh + i);
//
//            }
//            users.add(user);
//        }
//
//        users.forEach(userDao::createUser);
//
//        Assert.assertTrue(userDao.queryAll().size() == 10);
//
//        UserCondition condition = new UserCondition();
//        condition.setNamecondition(constren);
//        Assert.assertTrue(userDao.queryByCondition(condition).size() == 5);
//
//        condition.setNamecondition(constrzh);
//        Assert.assertTrue(userDao.queryByCondition(condition).size() == 5);
//
//        condition.setNamecondition("不能存在");
//        Assert.assertTrue(userDao.queryByCondition(condition).isEmpty());
//    }
//
//    @Test
//    public void updateBalance() {
//        User user = buildUser();
//        user.setBalance(500);
//
//        userDao.createUser(user);
//        User userInDB = userDao.queryByID(user);
//
//        double addBalance = 500d;
//        double updatedBalance = addBalance + userInDB.getBalance();
//        userInDB.setBalance(updatedBalance);
//
//        int affectRowcount = userDao.updateBalance(userInDB);
//        Assert.assertTrue(1 == affectRowcount);
//
//        userInDB = userDao.queryByID(userInDB);
//
//        Assert.assertNotNull(userInDB);
//        Assert.assertEquals(Double.valueOf(updatedBalance), Double.valueOf(userInDB.getBalance()));
//    }
//
//    @Test
//    public void updateComment() {
//
//    }
//}