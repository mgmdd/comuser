package com.user.service.dbservice.service;

import com.user.service.dbservice.domain.UserExp;

import java.util.List;

/**
 * 消费记录服务
 */
public interface PLUserExpService {
    /**
     * 新增一条记录
     *
     * @param exp 记录
     * @return
     */
    int create(UserExp exp);

    /**
     * 批量创建
     *
     * @param exps
     * @return
     */
//    int create(Collection<UserExp> exps);

    /**
     * 查询用户对应的所有记录
     *
     * @param userid
     * @return
     */
    List<UserExp> queryByUserID(String userid);

    /**
     * 查询一条记录数据
     * @param expid
     * @return
     */
//    UserExp queryByExpID(String expid);

    /**
     * @param user
     * @return
     */
//    default List<UserExp> queryByUser(User user) {
//        if (Objects.isNull(user)) {
//            return new ArrayList<>(0);
//        }
//        return queryByUserID(user.getUserID());
//    }

    /**
     * 查询库中所有的记录
     *
     * @return
     */
    List<UserExp> queryAll();

    /**
     * 删除一条记录
     *
     * @param expID
     * @return
     */
    int delete(String expID);

//    default int delete(UserExp userExp) {
//        if (Objects.isNull(userExp)) {
//            return 0;
//        }
//
//        return delete(userExp.getExpID());
//    }

    /**
     * 删除用户的消费记录
     *
     * @param userID
     * @return
     */
//    int deleteByUserID(String userID);

//    default int deleteByUser(User user) {
//        if (Objects.isNull(user)) {
//            return 0;
//        }
//        return deleteByUserID(user.getUserID());
//    }
}
