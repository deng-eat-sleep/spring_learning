package com.T03_spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

public class ResourceTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext();
        Resource resource = context.getResource("");
    }


}
