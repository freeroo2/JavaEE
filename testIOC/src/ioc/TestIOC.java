package com.mySpring.ioc;

public class TestIOC {
    static DefaultBeanFactory factory = new DefaultBeanFactory();

    public void testRegist() throws Exception{
        GenericBeanDefiniton bd = new GenericBeanDefiniton();
        bd.setBeanClass(TeacherBean.class);
        bd.setInitMethodName("init");
        factory.registerBeanDefinition("teacher",bd);
    }
    public static void testGetBean() throws Exception{
        TeacherBean t1 = (TeacherBean) factory.getBean("teacher");
        TeacherBean t2 = (TeacherBean) factory.getBean("teacher");
        t1.teach();
        t2.teach();
        System.out.println(t1==t2);
    }
}
