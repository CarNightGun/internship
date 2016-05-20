package com.km.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring IOC上下文工具类
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日下午1:09:11
 */
public class SpringUtil implements ApplicationContextAware {

    /**
     * 当前IOC
     */
    private static ApplicationContext acContext;

    /**
     * 设置当前上下文环境，此方法由spring自动装配
     */
    @Override
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
    	acContext = arg0;
    }

    /**
     * 从当前IOC获取bean
     * 
     * @param id
     *            bean的id
     * @return
     */
    public static Object getObject(String beanId) {
        Object object = null;
        object = acContext.getBean(beanId);
        return object;
    }
    
    /**
     * 从当前IOC获取bean
     * 
     * @param id
     *            bean的id
     * @return
     */
    public static <T> T getObject(Class<T> beanClass) {
        T t = null;
        t = acContext.getBean(beanClass);
        return t;
    }

}