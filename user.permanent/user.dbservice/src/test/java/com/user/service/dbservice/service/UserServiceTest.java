package com.user.service.dbservice.service;

import com.user.service.dbservice.UserTestUtil;
import com.user.service.dbservice.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class UserServiceTest {
    private DefaultPLUserService userService;

//    private ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring_dbservice.xml");

    @Before
    public void setUp() throws Exception {
        userService = new DefaultPLUserService();
        SqlSessionFactory sessionFactory = null;
        try (Reader reader = Resources.getResourceAsReader("mybatis.xml")) {
            Properties properties = new Properties();
            properties.put("jdbc.url", "jdbc:sqlite::resource:data/test.db");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sessionFactory = sqlSessionFactoryBuilder.build(reader, properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userService.setSessionFactory(sessionFactory);

        removeAll();
    }

    @After
    public void tearDown() throws Exception {
        removeAll();
    }

    private void removeAll() {
        userService.queryAll().forEach(user -> userService.deleteUser(user.getUserID()));
    }

    private void assertUserSame(User exp, User target) {
        Assert.assertNotNull(exp);
        Assert.assertNotNull(target);
        Assert.assertEquals(exp.getUserID(), target.getUserID());
        Assert.assertEquals(exp.getUserName(), target.getUserName());
        Assert.assertEquals(exp.getPhoneNo(), target.getPhoneNo());
        Assert.assertEquals(Double.valueOf(exp.getBalance()), Double.valueOf(target.getBalance()));
        Assert.assertEquals(exp.getRegDate(), target.getRegDate());
        Assert.assertEquals(exp.getComment(), target.getComment());
    }

    /**
     * 测试查询全部，有数据场景
     */
    @Test
    public void tesQueryAll0() {
        final int oldsize = userService.countUsers();

        final int addsize = 3;
        for (int i = 0; i < addsize; i++) {
            User user = UserTestUtil.buildUser();
            userService.createUser(user);
        }

        Assert.assertEquals(oldsize + addsize, userService.countUsers());
    }

    /**
     * 测试查询全部，无数据场景
     */
    @Test
    public void tesQueryAll_Empty() {
        //clear all users
        removeAll();

        Assert.assertTrue(userService.queryAll().isEmpty());
    }

    /**
     * 模糊查询测试
     */
    @Test
    public void testQueryByName0() {
        String constren = "conditon";
        String constrzh = "叶问";
        List<User> users = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            User user = UserTestUtil.buildUser();
            if (i < 5) {
                user.setUserName(constren + i);
            } else {
                user.setUserName(constrzh + i);

            }
            users.add(user);
        }
        users.stream().forEach(userService::createUser);

        Assert.assertTrue(userService.countUsers() == 10);

        Assert.assertTrue(userService.queryByName(constren).size() == 5);

        Assert.assertTrue(userService.queryByName(constrzh).size() == 5);
    }

    /**
     * 统计行数
     */
    public void count() {
        removeAll();
        int count = userService.countUsers();

        Assert.assertEquals(0, count);

        User user = UserTestUtil.buildUser();
        userService.createUser(user);

        count = userService.countUsers();
        Assert.assertEquals(1, count);
    }

    /**
     * 模糊查询，查询不存在内容
     */
    @Test
    public void testQueryByName1() {
        Assert.assertTrue(userService.queryByName("unexisted").isEmpty());
    }

    /**
     * 模糊查询，查询""
     */
    @Test
    public void testQueryByName2() {
        User user = UserTestUtil.buildUser();
        user.setUserName("");
        userService.createUser(user);
        Assert.assertTrue(userService.queryByName("").size() != 0);
    }

    /**
     * 模糊查询，查询 null
     */
    @Test
    public void testQueryByName3() {
        Assert.assertTrue(userService.queryByName(null).isEmpty());
    }

    /**
     * 通过id精确查询，存在的数据
     */
    @Test
    public void testQueryByID0() {
        User user = UserTestUtil.buildUser();
        int count = userService.createUser(user);
        Assert.assertEquals(1, count);

        User userInDB = userService.queryUserByID(user.getUserID());

        assertUserSame(user, userInDB);
    }

    /**
     * 通过id精确查询，【不】存在的数据
     */
    @Test
    public void testQueryByID1() {
        User userInDB = userService.queryUserByID("unexistedid");
        Assert.assertNull(userInDB);
    }

    /**
     * 传入null
     */
    @Test
    public void testQueryByID2() {
        String id = null;
        User userInDB = userService.queryUserByID(id);
        Assert.assertNull(userInDB);
    }

    /**
     * 创建用户：
     * 数据合理，不重复
     */
    @Test
    public void createUser0() {
        User user = UserTestUtil.buildUser();

        int count = userService.createUser(user);
        Assert.assertEquals(1, count);
    }

    /**
     * 创建用户：
     * 数据合理，ID重复
     */
    @Test
    public void createUser1() {
        User user = UserTestUtil.buildUser();

        int count = userService.createUser(user);
        Assert.assertEquals(1, count);

        User userSameID = UserTestUtil.buildUser();
        userSameID.setUserID(user.getUserID());
        int affectcount = userService.createUser(userSameID);
        Assert.assertTrue(0 == affectcount);
    }

    /**
     * 创建用户：
     * 数据合理：名称为空
     */
    @Test
    public void createUser2() {
        User user = UserTestUtil.buildUser();
        user.setUserName(null);

        int count = userService.createUser(user);
        Assert.assertTrue(0 == count);

        Assert.assertTrue(userService.queryUserByID(user.getUserID()) == null);

        user.setUserName("");
        count = userService.createUser(user);
        Assert.assertTrue(1 == count);
    }

    /**
     * 创建用户：
     * 数据合理：电话为空
     */
    @Test
    public void createUser3() {
        User user = UserTestUtil.buildUser();
        user.setPhoneNo(null);

        int count = userService.createUser(user);
        Assert.assertTrue(1 == count);

        user = UserTestUtil.buildUser();
        user.setPhoneNo("");
        count = userService.createUser(user);
        Assert.assertTrue(1 == count);
    }

    /**
     * 创建用户：
     * 数据合理：余额为空
     */
    @Test
    public void createUser4() {
        User user = new User();
        user.setUserID(UUID.randomUUID().toString());
//        user.setBalance(Math.random());
        user.setComment("");
        user.setPhoneNo("123");
        user.setRegDate(new Date(System.currentTimeMillis()));
        user.setUserName("UserName -" + System.currentTimeMillis());

        int count = userService.createUser(user);
        Assert.assertTrue(1 == count);

        User userInDB = userService.queryUserByID(user.getUserID());
        Assert.assertEquals(user.getBalance(), userInDB.getBalance(), 0);
    }

    /**
     * 创建用户：
     * 数据合理：备注为空
     */
    @Test
    public void createUser5() {
        User user = UserTestUtil.buildUser();
        user.setComment(null);

        int count = userService.createUser(user);
        Assert.assertTrue(1 == count);

        user = UserTestUtil.buildUser();
        user.setComment("");
        count = userService.createUser(user);
        Assert.assertTrue(1 == count);
    }

    /**
     * 创建用户：
     * 数据合理：除了ID都为空
     */
    @Test
    public void createUser6() {
        User user = new User();
        user.setUserID(UUID.randomUUID().toString());

        int count = userService.createUser(user);
        Assert.assertEquals(0, count);
    }

    /**
     * 创建用户：
     * 数据不合理：ID为空
     */
    @Test
    public void createUser7() {
        User user = new User();
        int count = userService.createUser(user);
        Assert.assertEquals(0, count);

        user.setUserID("");
        count = userService.createUser(user);
        Assert.assertEquals(0, count);
    }

    /**
     * 创建用户：
     * 数据不合理：user为null
     */
    @Test
    public void createUser8() {
        int count = userService.createUser(null);
        Assert.assertEquals(0, count);
    }

    /**
     * 删除用户---用户存在
     */
    @Test
    public void deleteUser0() {
        User user = UserTestUtil.buildUser();
        int count = userService.createUser(user);
        Assert.assertEquals(1, count);

        int deleteUsercount = userService.deleteUser(user.getUserID());

        Assert.assertEquals(1, deleteUsercount);
    }

    /**
     * 删除用户---用户【不】存在
     */
    @Test
    public void deleteUser1() {
        int deleteUsercount = userService.deleteUser("unexisted");

        Assert.assertEquals(0, deleteUsercount);
    }

    /**
     * 删除用户---参数为空
     */
    @Test
    public void deleteUser2() {
        User user = UserTestUtil.buildUser();
        userService.createUser(user);

        int deleteUsercount = userService.deleteUser(null);

        Assert.assertEquals(0, deleteUsercount);

        deleteUsercount = userService.deleteUser("");

        Assert.assertEquals(0, deleteUsercount);
    }

    @Test
    public void updateBalance0() {
        User user = UserTestUtil.buildUser();
        user.setBalance(100);

        userService.createUser(user);

        userService.updateBalance(user.getUserID(), 120);

        User userindb = userService.queryUserByID(user.getUserID());
        Assert.assertEquals(120d, userindb.getBalance(), 0.001);
    }

    @Test
    public void updateBalance1() {
        User user = UserTestUtil.buildUser();
        user.setBalance(100);
        userService.createUser(user);

        userService.updateBalance(user.getUserID(), -1);//will fail

        User userindb = userService.queryUserByID(user.getUserID());
        Assert.assertEquals(100, userindb.getBalance(), 0.001);
    }
}