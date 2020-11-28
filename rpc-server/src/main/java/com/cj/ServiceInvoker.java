package com.cj;

import com.cj.common.utils.ReflectionUtils;

/**
 * 服务执行
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service,Request request){
        return ReflectionUtils.invoke(
                service.getObject(),
                service.getMethod(),
                request.getParameters());
    }
}
