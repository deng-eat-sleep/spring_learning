package com.T02_spring.framework;

import org.springframework.beans.factory.annotation.Autowired;

import java.beans.Introspector;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * context容器
 */
public class ApplicationContextTest {


    private Class config;

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    //单例池
    private Map<String, Object> singletonObjectMap = new HashMap<>();

    //
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();


    /**
     * 容器加载
     *
     * 扫描 (生成BeanDefinition)-> 实例化单例bean(根据BeanDefinition实例化) ->
     */
    public ApplicationContextTest(Class config) throws Exception {
        this.config = config;

        scan();

        //todo 根据BeanDefinition创建bean
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();

            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjectMap.put(beanName, bean);
            }
        }
    }

    //创建bean
    private Object createBean(String beanName, BeanDefinition beanDefinition) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = beanDefinition.getType();
        Object instance = clazz.getConstructor().newInstance();

        for (Field field : clazz.getFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                field.set(instance,getBean(field.getName()));
            }
        }

        //初始化前
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            beanPostProcessor.postProcessBeforeInitialization(instance,beanName);
        }

        //初始化
        if (instance instanceof InitializingBean) {
            ((InitializingBean)instance).afterPropertiesSet();
        }

        //初始化后
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            beanPostProcessor.postProcessAfterInitialization(instance,beanName);
        }


        return instance;
    }


    //获取bean
    public Object getBean(String beanName) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new NullPointerException();
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition.getScope().equals("singleton")) {
            //单例，从单例池直接取
            Object bean = singletonObjectMap.get(beanName);
            if(bean == null){
                bean = createBean(beanName,beanDefinition);
                singletonObjectMap.put(beanName,bean);
            }
            return bean;
        } else {
            //原型，重新创建bean
            Object bean = createBean(beanName, beanDefinition);
            return bean;
        }

    }

    /**
     * 扫描
     */
    private void scan() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        if (config.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan configAnnotation = (ComponentScan) config.getAnnotation(ComponentScan.class);
            String path = configAnnotation.path().replace(".", "/");
            System.out.println("path :" + path);

            //类加载器，一个类加载器只扫描一个路径下的文件
            ClassLoader classLoader = ApplicationContextTest.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());

            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    String absolutePath = f.getAbsolutePath();

                    absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                    absolutePath = absolutePath.replace("/", ".");
                    Class<?> clazz = classLoader.loadClass(absolutePath);

                    //判断annotation
                    if (clazz.isAnnotationPresent(Component.class)) {

                        //实现BeanPostProcessor
                        if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                            BeanPostProcessor beanPostProcessor = (BeanPostProcessor) clazz.getConstructor().newInstance();
                            beanPostProcessorList.add(beanPostProcessor);
                        }


                        Component component = clazz.getAnnotation(Component.class);
                        String beanName = component.beanName();
                        if("".equals(beanName)){
                            beanName = Introspector.decapitalize(clazz.getSimpleName());
                        }

                        //todo 重点 生成BeanDefinition
                        BeanDefinition beanDefinition = new BeanDefinition();
                        beanDefinition.setType(clazz);

                        //获取作用域
                        if (clazz.isAnnotationPresent(Scope.class)) {
                            Scope scope = clazz.getAnnotation(Scope.class);
                            String value = scope.value();
                            beanDefinition.setScope(value);
                        } else {
                            // 默认单例
                            beanDefinition.setScope("singleton");
                        }

                        beanDefinitionMap.put(beanName, beanDefinition);
                    }

                }
            }
        }
    }
}
