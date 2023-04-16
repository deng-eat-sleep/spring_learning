package com.T02_spring;

import com.T02_spring.framework.ApplicationContextTest;
import com.T02_spring.test.service.UserService;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

public class ApplicationTest {

    public static void main(String[] args) throws Exception {
        //扫描 -> 创建bean
        ApplicationContextTest context = new ApplicationContextTest(AppConfig.class);

        UserService userService = (UserService) context.getBean("userService");


        System.out.println();
    }
    
    
}
