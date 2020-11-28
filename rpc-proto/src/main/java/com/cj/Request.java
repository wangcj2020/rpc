package com.cj;

import lombok.Data;

import java.util.Arrays;

/**
 * 表示RPC的一个请求
 */
@Data
public class Request {
    private ServiceDescriptor service;
    private Object[] parameters;

    @Override
    public String toString() {
//        return "Request{" +
//                "service=" + service +
//                ", parameters=" + Arrays.toString(parameters) +
//                '}';
        return "{\"service\":\"" + service + "\", \"parameters\":\"" + Arrays.toString(parameters) + "\"}";
    }
}
