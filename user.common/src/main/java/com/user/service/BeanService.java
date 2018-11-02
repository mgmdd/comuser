package com.user.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanService {
    private static ApplicationContext ctx;

    static {
        try {
            //String[] cfgs = {"spring/spring_appservice.xml", "spring/spring_dbservice.xml"};
            String cfgs = "classpath*:spring/spring_*service.xml";

            ctx = new ClassPathXmlApplicationContext(cfgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T getService(String id) {
        return (T) ctx.getBean(id);
    }

    public static <T> T getService(Class<T> clz) {
        return ctx.getBean(clz);
    }

    public static void main(String[] args) {

    }
}
