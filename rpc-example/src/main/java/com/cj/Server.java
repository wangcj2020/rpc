package com.cj;

import com.cj.impl.CalculateServiceImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 服务器
 */
public class Server {
    public static void main(String[] args) {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        assert addr != null;
        String ip=addr.getHostAddress().toString(); //获取本机ip：192.168.142.1
        // 使用默认配置创建服务端对象
        RpcServer server = new RpcServer(new RpcServerConfig());
        // 注册服务
        server.register(CalculateService.class,new CalculateServiceImpl());
        // 启动服务端
        server.start();
    }
}
