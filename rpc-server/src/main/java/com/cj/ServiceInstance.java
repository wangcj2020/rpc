package com.cj;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * 一个具体的服务
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    // 提供该服务的对象
    private Object object;
    // 该服务对应的对象中的方法
    private Method method;
}
