package com.user.service.dbservice.sessionholder;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public final class SqlSessionFactoryHolder {
    private static SqlSessionFactory sessionFactory;

    static {
        try (Reader reader = Resources.getResourceAsReader("mybatis.xml")) {
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SqlSessionFactoryHolder() {
    }

    /**
     * @return
     */
    public static final SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
