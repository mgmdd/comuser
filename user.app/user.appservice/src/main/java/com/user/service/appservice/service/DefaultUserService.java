package com.user.service.appservice.service;

import com.user.service.dbservice.domain.User;
import com.user.service.dbservice.service.PLUserService;

import java.util.List;

public class DefaultUserService implements UserService {
    private PLUserService plUserService;

    /**
     * 查询所有的用户信息
     *
     * @return
     */
    @Override
    public List<User> queryAll() {
        return plUserService.queryAll();
    }

    /**
     * 通过用户名模糊查找用户
     *
     * @param condition
     * @return
     */
    @Override
    public List<User> queryByName(String condition) {
        return plUserService.queryByName(condition);
    }

    /**
     * 查询用户信息
     * 查询用户信息
     *
     * @param userid 用户id
     * @return
     */
    @Override
    public User queryUserByID(String userid) {
        return plUserService.queryUserByID(userid);
    }

    /**
     * 总的用户数
     *
     * @return
     */
    @Override
    public int countUsers() {
        return plUserService.countUsers();
    }

    /**
     * 创建用户
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public int createUser(User user) {
        return plUserService.createUser(user);
    }

    /**
     * 删除用户
     *
     * @param usid 用户ID
     * @return
     */
    @Override
    public int deleteUser(String usid) {
        return plUserService.deleteUser(usid);
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
        return plUserService.updateBalance(userid, newBalance);
    }

    void setPlUserService(PLUserService plUserService) {
        this.plUserService = plUserService;
    }
}
