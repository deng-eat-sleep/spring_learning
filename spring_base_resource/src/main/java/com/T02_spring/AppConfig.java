package com.T02_spring;

import com.T02_spring.framework.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(path = "com.T02_spring.test.service")
public class AppConfig {
}
