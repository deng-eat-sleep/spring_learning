package com.T03_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;

import java.util.Collections;

public class TypeConvertTest {
    public static void main(String[] args) {

    }

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(Collections.singleton());

        return conversionServiceFactoryBean;
    }
}
