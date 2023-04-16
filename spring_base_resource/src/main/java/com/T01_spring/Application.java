package com.T01_spring;

import com.T01_spring.service.UserService;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = (UserService) context.getBean("userService");


        //声明式创建
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(com.T02_spring.test.service.UserService.class);
        //注册到容器
//        context.regisΩsterBeanDefinition("userService", beanDefinition);
    }


}
