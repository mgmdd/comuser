package com.user.service.dbservice.dao;

import com.user.service.dbservice.domain.User;
import com.user.service.dbservice.domain.UserCondition;
import com.user.service.dbservice.sessionholder.SqlSessionFactoryHolder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Map;

public class UserDao {
    private SqlSession session;
    private SqlSessionFactory sessionFactory;

    public UserDao(SqlSession session) {
        this.session = session;
        this.sessionFactory = SqlSessionFactoryHolder.getSessionFactory();
    }

    public List<User> queryAll() {
        return session.selectList("User.queryAll");
    }

    public List<User> queryByCondition(UserCondition condition) {
        return session.selectList("User.queryByCondition", condition);
    }

    public User queryByID(User user) {
        return (User) session.selectOne("User.queryUserByID", user);
    }

    public int createUser(User user) {
        return session.insert("User.createUser", user);
    }

    public int deleteUser(User user) {
        return session.delete("User.delete", user);
    }

    public int updateBalance(User user) {
        return session.update("User.updateBalance", user);

    }

    public int updateComment(User user) {
        return session.update("User.updateComment", user);
    }

    ///////================FOR TEST
    public Map<String, String> tQueryByID(@Param("userid") String userid) {
        return (Map)session.selectOne("User.", userid);
    }


}
