package com.T02_spring.test.service;

import com.T02_spring.framework.Component;
import com.T02_spring.framework.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

@Component(beanName = "userService")
public class UserService implements InitializingBean {

    @Autowired
    private OrderService orderService;

    public UserService() {
    }

    public UserService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void test(){
        System.out.println("test");
    }


    @Override
    public void afterPropertiesSet() {
        System.out.println("afterPropertiesSet------");
    }
}
