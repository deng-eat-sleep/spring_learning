package com.T01_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private OrderService orderService;


//    public UserService() {
//    }

    public UserService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void test(){
        System.out.println("test");
    }


}
