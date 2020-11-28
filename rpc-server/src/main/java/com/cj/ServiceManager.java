package com.cj;

import com.cj.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.sql.Ref;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务管理：包含注册服务及查询服务
 */

@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor,ServiceInstance> services;

    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();
    }

    /**
     * 注册服务
     * @param interfaceClass 服务接口类对象
     * @param bean 服务接口类对象的一个具体实现
     * @param <T>  服务接口类对象的一个具体实现的类型
     */
    public <T> void register(Class<T> interfaceClass, T bean){
        // 获取所有public方法（向外暴露的所有服务）
        Method[] methods = ReflectionUtils.getAllPublicMethods(interfaceClass);
        for(Method method:methods){
            // 获取ServiceDescriptor对象
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(interfaceClass, method);
            // System.out.println(serviceDescriptor);
            // 生成ServiceInstance对象
            ServiceInstance serviceInstance = new ServiceInstance(bean, method);
            // 注册服务
            services.put(serviceDescriptor,serviceInstance);
            log.info("register service: {} {}",serviceDescriptor.getClazz(),serviceDescriptor.getMethod());
        }
    }

    /**
     * 根据请求查询具体服务
     * @param request rpc请求
     * @return 服务的具体实现
     */
    public ServiceInstance lookup(Request request){
        return services.get(request.getService());
    }
}
