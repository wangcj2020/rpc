package com.cj.common.utils;

import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.Method;

public class ReflectionUtilsTest extends TestCase {

    @Test
    public void testNewInstance() {
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(testClass);
    }

    @Test
    public void testGetAllPublicMethods() {
        Method[] methods = ReflectionUtils.getAllPublicMethods(TestClass.class);
        for (Method method : methods){
            System.out.println(method.getName());
        }
    }

    @Test
    public void testInvoke() {
        Method[] methods = ReflectionUtils.getAllPublicMethods(TestClass.class);
        TestClass testClass = new TestClass();
        for (Method method : methods){
            Object o = ReflectionUtils.invoke(testClass, method);
            System.out.println(o);
        }
    }
}