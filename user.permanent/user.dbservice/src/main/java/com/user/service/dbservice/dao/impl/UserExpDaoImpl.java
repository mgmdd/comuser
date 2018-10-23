//package com.user.service.dbservice.dao.impl;
//
//import com.user.service.dbservice.model.User;
//import com.user.service.dbservice.model.DBUserExp;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.session.SqlSession;
//
//import java.util.List;
//
//public class UserExpDaoImpl {
//    private SqlSession session;
//
//    public UserExpDaoImpl(SqlSession session) {
//        this.session = session;
//    }
//
//    public int create(DBUserExp exp) {
//        return session.insert("DBUserExp.insert", exp);
//    }
//
//    public List<DBUserExp> queryByUser(User DBUser) {
//        return queryByUser(DBUser.getUserID());
//    }
//
//    public List<DBUserExp> queryByUser(@Param("userID") String userID) {
//        return session.selectList("DBUserExp.queryByUser", userID);
//    }
//
//    List<DBUserExp> queryAll() {
//        return session.selectList("DBUserExp.queryAll");
//    }
//
//    int delete(DBUserExp exp) {
//        return session.delete("DBUserExp.deleteExp", exp);
//    }
//}
