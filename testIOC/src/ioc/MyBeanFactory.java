package com.mySpring.ioc;
//对象实例的工厂
public interface MyBeanFactory {
    Object getBean(String name) throws Exception;
}
