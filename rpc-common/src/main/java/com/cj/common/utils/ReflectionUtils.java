package com.cj.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具类
 */
public class ReflectionUtils {

    /**
     * 根据全类名创建对象
     * @param clazz 要创建对象的全类名
     * @param <T> 要创建对象的类型
     * @return 创建好的对象
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 根据全类名获取该类中的public方法(不包含父类)
     * @param clazz 全类名
     * @return 指定类所有public方法
     */
    public static Method[] getAllPublicMethods(Class clazz){
        Method[] declaredMethods = clazz.getDeclaredMethods();
        List<Method> methods = new ArrayList<>();

        for (Method method:declaredMethods) {
            if(Modifier.isPublic(method.getModifiers())){
                methods.add(method);
            }
        }

        return methods.toArray(new Method[0]);
    }

    /**
     * 调用某对象的方法
     * @param obj 要调用方法的对象
     * @param method 该对象要调用的方法
     * @param args 参数
     * @return 方法返回值
     */
    public static Object invoke(Object obj,Method method,Object... args){
        try {
            return method.invoke(obj,args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
