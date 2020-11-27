package com.cj;

/**
 * 1、监听端口
 * 2、接受请求并处理
 * 3、结束监听
 */
public interface TransportServer {
    void init(int port,RequestHandler handler);

    void start();

    void stop();
}
