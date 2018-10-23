//package com.user.service.dbservice.dao.impl;
//
//import com.user.model.user.User;
//import com.user.service.dbservice.dao.UserDao;
//import com.user.service.dbservice.model.DBUser;
//import com.user.service.dbservice.sessionmgr.SqlSessionHolder;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.session.SqlSession;
//
//import java.util.List;
//
//public class UserDaoImpl implements UserDao {
//    private SqlSession session;
//
//    public UserDaoImpl() {
//        this(SqlSessionHolder.getSqlSession());
//    }
//
//    private UserDaoImpl(SqlSession session) {
//        this.session = session;
//    }
//
//    /**
//     * 统计用户数
//     *
//     * @return 用户数
//     */
//    @Override
//    public int count() {
//        return 0;
//    }
//
//    public List<DBUser> queryAll() {
//        return session.selectList("DBUser.queryAll");
//    }
//
//    /**
//     * 根据用户名条件模糊查询
//     *
//     * @param nameCondition 待查询的用户名
//     * @return 符合条件的用户信息
//     */
//    @Override
//    public List<DBUser> queryByUserName(@Param("username") String nameCondition) {
//        return null;
//    }
//
//    public DBUser queryByID(@Param("userID") String userID) {
//        return session.selectOne("DBUser.queryByID", userID);
//    }
//
//    public DBUser queryByID(DBUser DBUser) {
//        return queryByID(DBUser.getUserID());
//    }
//
//    /**
//     * 新建用户；
//     * 数据库会自行判断userid是否重复
//     *
//     * @param user 待创建用户信息
//     * @return 创建结果。1：成功， 0：失败
//     */
//    @Override
//    public int createUser(DBUser user) {
//        return 0;
//    }
//
//    public int createUser(User DBUser) {
//        return session.insert("DBUser.createUser", DBUser);
//    }
//
//    public int deleteUser(@Param("userID") String userID) {
//        return session.delete("DBUser.delete", userID);
//    }
//
//    public int updateBalance(DBUser DBUser) {
//        return session.update("DBUser.updateBalance", DBUser);
//    }
//
//    public int updateComment(DBUser DBUser) {
//        return session.update("DBUser.updateComment", DBUser);
//    }
//}
