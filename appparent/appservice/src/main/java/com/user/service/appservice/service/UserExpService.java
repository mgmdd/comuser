package com.user.service.appservice.service;

import com.user.service.dbservice.domain.UserExp;

import java.util.List;

/**
 * APP服务
 */
public interface UserExpService {
    /**
     * 新增一条记录
     *
     * @param exp 记录
     * @return
     */
    int create(UserExp exp);

    /**
     * 查询用户对应的所有记录
     *
     * @param userid
     * @return
     */
    List<UserExp> queryByUserID(String userid);
}
