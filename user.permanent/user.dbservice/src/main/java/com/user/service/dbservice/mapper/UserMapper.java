package com.user.service.dbservice.mapper;

import com.user.service.dbservice.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户mapper
 */
public interface UserMapper {

    /**
     * 查询所有的用户信息
     *
     * @return
     */
    List<User> queryAll();

    /**
     * 通过用户名模糊查找用户
     *
     * @param namecondtion
     * @return
     */
    List<User> queryByName(@Param("namecondition") String namecondtion);

    /**
     * 查询用户信息
     *
     * @param userid 用户id
     * @return
     */
    User queryByID(@Param("userid") String userid);

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
    int deleteUser(@Param("userid") String usid);

    /**
     * 更新余额
     *
     * @param userid     用户ID
     * @param newbalance 更新后的余额
     * @return
     */
    int updateBalance(@Param("userid") String userid, @Param("newbalance") double newbalance);

    /**
     * 更新用户备注
     *
     * @param user 用户信息
     * @return
     */
//    int updateComment(User user);

    /**
     * 计算行数
     *
     * @return 行数
     */
    int count();
}
