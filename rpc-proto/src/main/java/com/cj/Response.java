package com.cj;

import lombok.Data;

/**
 * 表示RPC的一个响应
 */
@Data
public class Response {
    private int code = 0; // 响应码，0表示正确响应，非零表示出错
    private String message = "ok"; // 出错原因
    private Object data; // 响应数据

    @Override
    public String toString() {
//        return "Response{" +
//                "code=" + code +
//                ", message='" + message + '\'' +
//                ", data=" + data +
//                '}';
        return "{\"code\":\"" + code + "\", \"message\":\"" + message + "\", \"data\":\"" + data + "\"}";
    }
}
