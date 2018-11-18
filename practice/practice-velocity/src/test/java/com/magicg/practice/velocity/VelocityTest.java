package com.magicg.practice.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.junit.Test;

import java.io.StringWriter;
import java.text.SimpleDateFormat;

public class VelocityTest {

    @Test
    public void testit() {
        //初始化velocityengine
        VelocityEngine engine = new VelocityEngine();

        /**
         * 参考默认属性配置：org/apache/velocity/runtime/defaults/velocity.properties
         *resource.loader = file
         *file.resource.loader.description = Velocity File Resource Loader
         *file.resource.loader.class = org.apache.velocity.runtime.resource.loader.FileResourceLoader
         *file.resource.loader.path = .
         *file.resource.loader.cache = false
         *file.resource.loader.modificationCheckInterval = 2
         */
        //资源加载类型：classpath
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        engine.init();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(System.currentTimeMillis());

        /**
         * 获取vm文件
         */
        Template template = engine.getTemplate("Service.vm", "UTF-8");

        /**
         * 初始化context，保存要渲染至vm的属性对象
         */
        VelocityContext context = new VelocityContext();
        context.put("package_name", "com.magicg");
        context.put("model", "CreateUserService");
        context.put("ctime", date);
        context.put("author", "MagicG");
        System.out.println(context.getCurrentResource());
        System.out.println(context.getCurrentTemplateName());
        for (String key : context.getKeys()) {
            System.out.println(key);
        }

        //输出目标
        StringWriter sw = new StringWriter();

        //begin renderer
        template.merge(context, sw);

        System.out.println(sw);
    }
}
