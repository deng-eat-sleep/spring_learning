package com.T02_spring.test.service;

import com.T02_spring.framework.BeanPostProcessor;
import com.T02_spring.framework.Component;
import org.springframework.beans.BeansException;

/**
 * 初始化后
 */
@Component
public class PostProcessorAfterInitialization implements BeanPostProcessor {

    /**
     * 重写
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("after: " + beanName);
        return bean;
    }

}
