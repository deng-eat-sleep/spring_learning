package com.T04_spring;

import com.T02_spring.AppConfig;
import com.T02_spring.test.service.UserService;

public class ApplicationTest {

    public static void main(String[] args) throws Exception {
        //扫描 -> 创建bean
        AnnotaionConfigApplication context = new AnnoConfigApplication(AppConfig.class);

        UserService userService = (UserService) context.getBean("userService");


        System.out.println();
    }
    
    
}
