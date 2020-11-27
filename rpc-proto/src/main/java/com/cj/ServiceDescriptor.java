package com.cj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
