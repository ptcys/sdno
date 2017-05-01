package com.bnrc.sdn.util;

import com.bnrc.sdn.mongoClient.MongoAutoConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;

@Component
@AutoConfigureAfter(MongoAutoConfiguration.class)
public class SpringContextUtil  {

    // Spring应用上下文环境
    private static ApplicationContext applicationContext;
    private static String id ;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    public static void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
        id = "2";
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static String getId() {
        return id;
    }

    /**
     * 获取对象 这里重写了bean方法，起主要作用
     */
    public static Object getBeanById(String beanId) throws BeansException {
        return applicationContext.getBean(beanId);
    }
    public static Object getBeanByClass(Class c){
        return applicationContext.getBean(c);
    }

}