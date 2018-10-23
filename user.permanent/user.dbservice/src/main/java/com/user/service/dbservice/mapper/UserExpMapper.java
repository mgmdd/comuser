package com.user.service.dbservice.mapper;

import com.user.service.dbservice.domain.UserExp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserExp mapper
 */
public interface UserExpMapper {
    /**
     * create one record
     *
     * @param exp exp to create
     * @return affect row count
     */
    int create(UserExp exp);

    /**
     * 查询对应用户的记录信息
     *
     * @param userid 用户id
     * @return 记录信息
     */
    List<UserExp> queryByUser(@Param("userid") String userid);

    /**
     * 查询一条记录
     * @param expid
     * @return
     */
    UserExp queryByExpID(@Param("expid") String expid);

    /**
     * 查询所有记录列表
     *
     * @return
     */
    List<UserExp> queryAll();

    /**
     * 删除一条记录
     *
     * @param expid
     * @return
     */
    int delete(@Param("expid") String expid);

    /**
     * 删除所有用户的记录信息
     *
     * @param userid 用户ID
     * @return
     */
    int deleteByUser(@Param("userid") String userid);
}
