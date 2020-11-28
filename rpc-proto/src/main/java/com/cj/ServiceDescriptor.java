package com.cj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 记录服务信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazz; //服务类名
    private String method; //服务方法名
    private String returnType; //返回值类型
    private String[] parameterTypes; //参数类型

    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", returnType='" + returnType + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                '}';
    }

    /**
     * 根据clazz和method生成一个ServiceDescriptor对象
     * @param clazz 服务类名
     * @param method 服务方法名
     * @return ServiceDescriptor对象
     */
    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
        serviceDescriptor.setClazz(clazz.getName());
        serviceDescriptor.setMethod(method.getName());
        serviceDescriptor.setReturnType(method.getReturnType().getName());

        Class<?>[] types = method.getParameterTypes();
        String[] parameterTypes = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            parameterTypes[i] = types[i].getName();
        }
        serviceDescriptor.setParameterTypes(parameterTypes);
        return serviceDescriptor;
    }

    // ServiceManager中服务注册时用ServiceDescriptor作为ConCurrentHashMap的key值，因此需要重写hashCode、equals方法
    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false; // obj为空或与this类型不同时返回false

        // obj与this类型相同，强转
        ServiceDescriptor that = (ServiceDescriptor) obj;
        return this.toString().equals(that.toString());
    }
}
