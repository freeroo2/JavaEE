package com.mySpring.ioc;
/*
用来描述这个bean是如何被定义出来的
在此代码中bean定义只通过类构造器来创建bean
singleton : 单例的
prototype : 多例的
 */
public interface MyBeanDefinition {
    final static String SINGLETION = "singleton";
    final static String PROTOTYPE = "prototype";
    Class<?> getBeanClass();
    String getScope();
    boolean isSingleton();
    boolean isPrototype();
    String getInitMethodName();
}
