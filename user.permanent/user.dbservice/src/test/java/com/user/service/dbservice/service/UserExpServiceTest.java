package com.user.service.dbservice.service;

import com.user.model.BalanceOperation;
import com.user.service.BeanService;
import com.user.service.dbservice.UserTestUtil;
import com.user.service.dbservice.domain.UserExp;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

public class UserExpServiceTest {
    private PLUserExpService service;

    @Before
    public void setUp() throws Exception {
        service = BeanService.getService(PLUserExpService.class);//new DefaultPLUserExpService();
        removeall();
    }

    @After
    public void tearDown() throws Exception {
        removeall();
    }

    private void removeall() {
        try {
            List<UserExp> userExps = service.queryAll();
            userExps.stream().map(UserExp::getExpID).forEach(service::delete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private UserExp buildExp(String userID, double nowBalance, int op) {
        return UserTestUtil.buildExp(userID, nowBalance, op);
    }

    private void isEqual(UserExp exp, UserExp target) {
        Assert.assertNotNull(exp);
        Assert.assertNotNull(target);

        Assert.assertEquals(exp.getExpID(), target.getExpID());
        Assert.assertEquals(exp.getUserID(), target.getUserID());
        Assert.assertEquals(exp.getExpDate(), target.getExpDate());
        Assert.assertEquals(exp.getOperation(), target.getOperation());
        Assert.assertEquals(Double.valueOf(exp.getCurrentBalance()), Double.valueOf(target.getCurrentBalance()));
        Assert.assertEquals(Double.valueOf(exp.getExpValue()), Double.valueOf(target.getExpValue()));
    }

    /**
     * 正常数据创建
     */
    @Test
    public void create0() {
        String userID = UUID.randomUUID().toString();
        double nowBalance = 1000;
        UserExp exp = buildExp(userID, nowBalance, BalanceOperation.ADD);

        int affectRow = service.create(exp);
        Assert.assertEquals(1, affectRow);
    }

    /**
     * 创建重复数据
     */
    @Test
    public void create1() {
        String userID = UUID.randomUUID().toString();
        double nowBalance = 1000;
        UserExp exp = buildExp(userID, nowBalance, BalanceOperation.ADD);

        int affectRow = service.create(exp);

        affectRow = service.create(exp);

        Assert.assertEquals(0, affectRow);
    }

    /**
     * 输入 null
     */
    @Test
    public void create2() {
        int affectRow = service.create(null);

        Assert.assertEquals(0, affectRow);
    }

    /**
     * userID 为null或者 ""
     */
    @Test
    public void create3() {
        double nowBalance = 1000;
        UserExp exp = buildExp(null, nowBalance, BalanceOperation.ADD);

        int affectRow = service.create(exp);
        Assert.assertEquals(0, affectRow);

        exp = buildExp("", nowBalance, BalanceOperation.ADD);
        affectRow = service.create(exp);
        Assert.assertEquals(0, affectRow);
    }

    /**
     * expid为null或者""
     */
    @Test
    public void create4() {
        String userID = UUID.randomUUID().toString();
        double nowBalance = 1000;
        UserExp exp = buildExp(userID, nowBalance, BalanceOperation.ADD);
        exp.setExpID(null);

        int affectRow = service.create(exp);
        Assert.assertEquals(0, affectRow);

        exp.setExpID("");
        affectRow = service.create(exp);
        Assert.assertEquals(0, affectRow);
    }

    @Test
    public void queryByUserID() {
        String userID = UUID.randomUUID().toString();
        double nowBalance = 1000;
        UserExp exp = buildExp(userID, nowBalance, BalanceOperation.ADD);

        int affectRow = service.create(exp);
        exp = buildExp(userID, nowBalance, BalanceOperation.ADD);

        affectRow = service.create(exp);

        Assert.assertEquals(2, service.queryByUserID(userID).size());
    }

//    @Test
//    public void queryByExpID() {
//    }

//    @Test
//    public void queryByUser() {
//    }

//    @Test
//    public void queryAll() {
//        final int oldsize = service.queryAll().size();
//
//        final int addsize = 3;
//        for (int i = 0; i < addsize; i++) {
//            String userID = UUID.randomUUID().toString();
//            double nowBalance = 1000;
//            UserExp exp = buildExp(userID, nowBalance, BalanceOperation.ADD);
//
//            int affectRow = service.create(exp);
//        }
//
//        Assert.assertEquals(oldsize + addsize, service.queryAll().size());
//    }

//    @Test
//    public void delete() {
//    }
//
//    @Test
//    public void delete1() {
//    }

//    /**
//     * user id存在
//     */
//    @Test
//    public void deleteByUserID() {
//        String userID = UUID.randomUUID().toString();
//        double nowBalance = 1000;
//        UserExp exp = buildExp(userID, nowBalance, BalanceOperation.ADD);
//        service.create(exp);
//
//        int i = service.deleteByUserID(userID);
//        Assert.assertEquals(1, i);
//    }

//    @Test
//    public void deleteByUser() {
//    }
}