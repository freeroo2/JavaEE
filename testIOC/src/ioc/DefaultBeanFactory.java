package com.mySpring.ioc;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
/*
bean工厂实现类
 */
public class DefaultBeanFactory implements  MyBeanFactory,MyBeanDefinitionRegistry{
    //定义两个集合存放bean实例和beanDefinition
    private Map<String,Object> beanMap = new ConcurrentHashMap<>();
    private Map<String,MyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    //注册bean定义，需要给定唯一bean名称和bean定义，放到bean定义集合中
    @Override
    public void registerBeanDefinition(String beanName, MyBeanDefinition myBeanDefinition) throws Exception{
        /*
        Objects.requireNonNull函数用来进行参数非空检查,涉及到一个很重要的编程思想, 就是 Fail-fast 思想,
        翻译过来就是, 让错误尽可能早的出现, 不要等到我们很多工作执行到一半之后才抛出异常
         */
        Objects.requireNonNull(beanName,"beanName不能为空");
        Objects.requireNonNull(myBeanDefinition,"myBeanDefinition不能为空");
        if(beanDefinitionMap.containsKey(beanName)){
            throw new Exception("已存在【"+beanName+"】的bean定义");
        }
        beanDefinitionMap.put(beanName,myBeanDefinition);
    }

    /*
    获得bean定义
     */
    @Override
    public MyBeanDefinition getBeanDefinition(String beanName){
        return beanDefinitionMap.get(beanName);
    }

    /*
    是否存在bean定义
     */
    @Override
    public boolean containsBeanDefinition(String beanName){
        return beanDefinitionMap.containsKey(beanName);
    }

    /*
    getBean：获得bean的门面方法，用构造器来创建对象
    */
    @Override
    public Object getBean(String name) throws Exception{
        return doGetBean(name);
    }
    /*
    getBean的具体逻辑
     */
    private Object doGetBean(String beanName) throws Exception{
        Objects.requireNonNull(beanName,"beanName不能为空");
        Object instance = beanMap.get(beanName);
        //如果bean已存在，则直接返回
        if(instance!=null){
            return instance;
        }
    //从beanDefinitionMap中获取bean定义，如果没定义，就返回异常
        MyBeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        Objects.requireNonNull(beanDefinition,"beanDefinition不能为空");
        Class<?> class1 = beanDefinition.getBeanClass();
        Objects.requireNonNull(class1,"bean定义中class类型不能为空");
        //调用bean定义中的构造方法来创建实例
        instance = class1.newInstance();

        //实例已创建好，通过反射执行bean的init方法
        String initMethodName = beanDefinition.getInitMethodName();
        if(initMethodName!=null){
            Method method = class1.getMethod(initMethodName,null);
            method.invoke(instance,null);
        }

        //将单例bean放到map中，下次获取此对象可直接返回
        if(beanDefinition.isSingleton()){
            beanMap.put(beanName,instance);
        }
        return instance;
    }
}

