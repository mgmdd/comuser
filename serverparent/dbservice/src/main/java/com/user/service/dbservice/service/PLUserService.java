package com.user.service.dbservice.service;

import com.user.service.dbservice.domain.User;

import java.util.List;

/**
 * 提供用户操作的服务接口
 */
public interface PLUserService {

    /**
     * 查询所有的用户信息
     *
     * @return
     */
    List<User> queryAll();

    /**
     * 通过用户名模糊查找用户
     *
     * @param condition
     * @return
     */
    List<User> queryByName(String condition);

    /**
     * 查询用户信息
     * 查询用户信息
     *
     * @param userid 用户id
     * @return
     */
    User queryUserByID(String userid);

    /**
     * 总的用户数
     *
     * @return
     */
    int countUsers();

    /**
     * 创建用户
     *
     * @param user 用户信息
     * @return
     */
    int createUser(User user);


    /**
     * 删除用户
     *
     * @param usid 用户ID
     * @return
     */
    int deleteUser(String usid);

    /**
     * 更新余额
     *
     * @param userid     用户ID
     * @param newBalance 最新余额
     * @return
     */
    int updateBalance(String userid, double newBalance);


    /**
     * 更新用户备注
     *
     * @param user 用户信息
     * @return
     */
//    int updateComment(User user);
}
