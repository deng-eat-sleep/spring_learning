package com.test;

import com.fasterxml.jackson.core.util.Instantiatable;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Component
public class Service{

    @Autowired
    private Service service;

    //初始化前
    @PostConstruct
    public void test(){
        System.out.println();
    }

    @Transactional
    public void tetw(){
        System.out.println();
    }
}
