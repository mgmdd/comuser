package com.user.service.dbservice.service;

import com.user.service.dbservice.domain.User;
import com.user.service.dbservice.mapper.UserMapper;
import com.user.service.dbservice.sessionholder.SqlSessionFactoryHolder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Objects;

/**
 * 提供用户操作的服务接口
 */
public class DefaultPLUserService implements PLUserService {
    private SqlSessionFactory sessionFactory;

    public DefaultPLUserService() {
        this.sessionFactory = SqlSessionFactoryHolder.getSessionFactory();
    }

    /**
     * 查询所有的用户信息
     *
     * @return
     */
    public List<User> queryAll() {
        try (SqlSession session = sessionFactory.openSession(true)) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            return userMapper.queryAll();
        }
    }

    /**
     * 通过用户名模糊查找用户
     *
     * @param condition
     * @return
     */
    public List<User> queryByName(String condition) {
        try (SqlSession session = sessionFactory.openSession(true)) {
            UserMapper userMapper = session.getMapper(UserMapper.class);

            return userMapper.queryByName(Objects.isNull(condition) ? "" : condition);
        }
    }

    /**
     * 查询用户信息
     * 查询用户信息
     *
     * @param userid 用户id
     * @return
     */
    public User queryUserByID(String userid) {
        try (SqlSession session = sessionFactory.openSession(true)) {
            UserMapper userMapper = session.getMapper(UserMapper.class);

            return userMapper.queryByID(userid);
        }
    }

    /**
     * 总的用户数
     *
     * @return
     */
    public int countUsers() {
        try (SqlSession session = sessionFactory.openSession(true)) {
            UserMapper userMapper = session.getMapper(UserMapper.class);

            return userMapper.count();
        }
    }

    /**
     * 创建用户
     *
     * @param user 用户信息
     * @return
     */
    public int createUser(User user) {
        if (isInputUserInvalid(user)) {
            return 0;
        }

        try (SqlSession session = sessionFactory.openSession(true)) {
            UserMapper userMapper = session.getMapper(UserMapper.class);

            return userMapper.createUser(user);
        } catch (Exception e) {
            return 0;
        }
    }

    private boolean isInputUserInvalid(User user) {
        if (Objects.isNull(user)) {
            return true;
        }
        if (Objects.isNull(user.getUserID()) || user.getUserID().isEmpty()) {
            return true;
        }
        if (Objects.isNull(user.getUserName())) {
            return true;
        }
        if (user.getBalance() < 0.0d) {
            return true;
        }
        if (Objects.isNull(user.getRegDate())) {
            return true;
        }
        return false;
    }


    /**
     * 删除用户
     *
     * @param usid 用户ID
     * @return
     */
    public int deleteUser(String usid) {
        try (SqlSession session = sessionFactory.openSession(true)) {
            UserMapper userMapper = session.getMapper(UserMapper.class);

            return userMapper.deleteUser(usid);
        }
    }

    /**
     * 更新余额
     *
     * @param userid     用户ID
     * @param newBalance 最新余额
     * @return
     */
    @Override
    public int updateBalance(String userid, double newBalance) {
        if (newBalance < 0.0D) {
            return 0;
        }
        try (SqlSession session = sessionFactory.openSession(true)) {
            UserMapper userMapper = session.getMapper(UserMapper.class);

            return userMapper.updateBalance(userid, newBalance);
        }
    }


    /**
     * 更新用户备注
     *
     * @param user 用户信息
     * @return
     */
//    public int updateComment(User user) {
//        try (SqlSession session = sessionFactory.openSession(true)) {
//            UserMapper userMapper = session.getMapper(UserMapper.class);
//
//            return userMapper.updateComment(user);
//        }
//    }
}
