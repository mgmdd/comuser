package com.user.service.dbservice.dao;

import com.user.service.dbservice.domain.User;
import com.user.service.dbservice.domain.UserExp;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserExpDao {
    private SqlSession session;

    public UserExpDao(SqlSession session) {
        this.session = session;
    }

    public int create(UserExp exp) {
        return session.insert("UserExp.insert", exp);
    }

    public List<UserExp> queryByUser(User user) {
        return session.selectList("UserExp.queryByUser", user);
    }

    public List<UserExp> queryAll() {
        return session.selectList("UserExp.queryAll");
    }

    public int delete(UserExp exp) {
        return session.delete("UserExp.deleteExp", exp);
    }
}
