package com.mySpring.ioc;

public class TeacherBean {
    public void teach(){
        System.out.println(this+"执行了teach方法，老师要上课了！");
    }
    public void init(){
        System.out.println("Teacher类的初始化init方法被执行了");
    }
}
