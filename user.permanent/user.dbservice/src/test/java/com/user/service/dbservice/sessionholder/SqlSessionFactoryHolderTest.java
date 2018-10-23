package com.user.service.dbservice.sessionholder;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;

public class SqlSessionFactoryHolderTest {

    private SqlSession session;
    @org.junit.Before
    public void setUp() throws Exception {
        session = SqlSessionFactoryHolder.getSessionFactory().openSession(true);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        session.close();
    }

    @org.junit.Test
    public void getSessionFactory() {

        SqlSessionFactory sessionFactory = SqlSessionFactoryHolder.getSessionFactory();

        Assert.assertNotNull(sessionFactory);

        SqlSession sqlSession = sessionFactory.openSession(true);

        sqlSession.close();
    }
//
//    @Test
//    public void testMap() {
//
//        session.selectOne(, )
//    }
}