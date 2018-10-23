package com.user.service.appservice.service;

import com.user.service.dbservice.domain.UserExp;
import com.user.service.dbservice.service.PLUserExpService;

import java.util.List;

public class DefaultUserExpService implements UserExpService {
    private PLUserExpService service;

    /**
     * 新增一条记录
     *
     * @param exp 记录
     * @return
     */
    @Override
    public int create(UserExp exp) {
        return service.create(exp);
    }

    /**
     * 查询用户对应的所有记录
     *
     * @param userid
     * @return
     */
    @Override
    public List<UserExp> queryByUserID(String userid) {
        return service.queryByUserID(userid);
    }
}
