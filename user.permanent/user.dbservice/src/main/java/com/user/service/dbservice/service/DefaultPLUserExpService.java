package com.user.service.dbservice.service;

import com.user.service.dbservice.domain.UserExp;
import com.user.service.dbservice.mapper.UserExpMapper;
import com.user.service.dbservice.sessionholder.SqlSessionFactoryHolder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Objects;

/**
 *
 */
public class DefaultPLUserExpService implements PLUserExpService {
    private SqlSessionFactory sessionFactory;

    public DefaultPLUserExpService() {
        this.sessionFactory = SqlSessionFactoryHolder.getSessionFactory();
    }

    /**
     * 新增一条记录
     *
     * @param exp 记录
     * @return
     */
    @Override
    public int create(UserExp exp) {
        if (expInvalid(exp)) {
            return 0;
        }
        try (SqlSession session = sessionFactory.openSession(true)) {
            UserExpMapper mapper = session.getMapper(UserExpMapper.class);

            return mapper.create(exp);
        } catch (Exception e) {
            return 0;
        }
    }

    private boolean expInvalid(UserExp exp) {
        if (Objects.isNull(exp)) {
            return true;
        }
        if (Objects.isNull(exp.getExpID()) || exp.getExpID().isEmpty()) {
            return true;
        }

        if (Objects.isNull(exp.getUserID()) || exp.getUserID().isEmpty()) {
            return true;
        }

        if (Objects.isNull(exp.getExpDate())) {
            return true;
        }


        return false;
    }

    /**
     * 批量创建
     *
     * @param exps
     * @return
     */
//    @Override
//    public int create(Collection<UserExp> exps) {
//        return 0;
//    }

    /**
     * 查询用户对应的所有记录
     *
     * @param userid
     * @return
     */
    @Override
    public List<UserExp> queryByUserID(String userid) {
        try (SqlSession session = sessionFactory.openSession(true)) {
            UserExpMapper mapper = session.getMapper(UserExpMapper.class);

            return mapper.queryByUser(userid);
        }
    }

    /**
     * 查询一条记录数据
     *
     * @param expid
     * @return
     */
//    @Override
//    public UserExp queryByExpID(String expid) {
//        try (SqlSession session = sessionFactory.openSession(true)) {
//            UserExpMapper mapper = session.getMapper(UserExpMapper.class);
//
//            return mapper.queryByExpID(expid);
//        }
//    }

    /**
     * 查询库中所有的记录
     *
     * @return
     */
    @Override
    public List<UserExp> queryAll() {
        try (SqlSession session = sessionFactory.openSession(true)) {
            UserExpMapper mapper = session.getMapper(UserExpMapper.class);

            return mapper.queryAll();
        }
    }

    /**
     * 删除一条记录
     *
     * @param expID
     * @return
     */
    @Override
    public int delete(String expID) {
        try (SqlSession session = sessionFactory.openSession(true)) {
            UserExpMapper mapper = session.getMapper(UserExpMapper.class);

            return mapper.delete(expID);
        }
    }

    /**
     * 删除用户的消费记录
     *
     * @param userID
     * @return
     */
//    @Override
//    public int deleteByUserID(String userID) {
//        try (SqlSession session = sessionFactory.openSession(true)) {
//            UserExpMapper mapper = session.getMapper(UserExpMapper.class);
//
//            return mapper.deleteByUser(userID);
//        }
//    }
}
