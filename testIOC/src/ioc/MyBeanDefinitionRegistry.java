package com.mySpring.ioc;
/*
有了bean定义的接口，我们还是不能放入工厂中，还得将bean注册到工厂中
我们再写一个bean定义注册（BeanDefinitionRegistry）的接口
此接口要提供注册的功能、获取BeanDefinition的功能以及检测是否已有bean的功能
 */
public interface MyBeanDefinitionRegistry {

    //注册
    void registerBeanDefinition(String beanName, MyBeanDefinition myBeanDefinition) throws Exception;

    //获取BeanDefinition
    MyBeanDefinition getBeanDefinition(String beanName);

    //检测是否已有bean
    boolean containsBeanDefinition(String beanName);
}
