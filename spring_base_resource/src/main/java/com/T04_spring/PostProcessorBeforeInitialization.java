package com.T04_spring;

import com.T02_spring.framework.BeanPostProcessor;
import com.T02_spring.framework.Component;
import org.springframework.beans.BeansException;

/**
 * 初始化前
 */
@Component
public class PostProcessorBeforeInitialization implements BeanPostProcessor {

    /**
     * 重写
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("before: " + beanName);
        return bean;
    }

}
