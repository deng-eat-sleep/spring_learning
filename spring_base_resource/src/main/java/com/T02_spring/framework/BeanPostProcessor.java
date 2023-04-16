package com.T02_spring.framework;

import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

/**
 * 后置处理器
 * 针对所有bean
 * 用于aop,连接Spring IOC和AOP的桥梁
 */
public interface BeanPostProcessor {

    default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
