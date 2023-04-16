package com.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.test.Service;

public class Test01_context {

    public static void main(String[] args) {

        //IoC容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


        //Bean 容器创建 通过DI进行初始化属性
        //service.class -> 无参构造方式 -> 对象 -> DI(属性赋值)-> 初始化 -> Bean
        Service service = context.getBean(Service.class);
        service.tetw();

        //new 属性不进行初始化
        Service service2 = new Service();

        System.out.println("aaa");

    }


}
