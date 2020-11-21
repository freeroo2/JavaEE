package com.mySpring.ioc;

import javax.print.DocFlavor;
import java.security.PrivilegedExceptionAction;
import java.util.Objects;

public class GenericBeanDefiniton implements MyBeanDefinition{

    private Class<?> beanClass;

    private String scope = MyBeanDefinition.SINGLETION;

    private String initMethodName;

    public void setBeanClass(Class<?> beanClass){
        this.beanClass = beanClass;
    }
    public void setScope(String scope){
        this.scope = scope;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    @Override
    public Class<?> getBeanClass() {
        return beanClass;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public boolean isSingleton(){
        return Objects.equals(scope,MyBeanDefinition.SINGLETION);
    }

    @Override
    public boolean isPrototype(){
        return Objects.equals(scope,MyBeanDefinition.PROTOTYPE);
    }

    @Override
    public  String getInitMethodName(){
        return initMethodName;
    }
}
