package com.cj;

import java.io.InputStream;

/**
 * 1、请求连接
 * 2、发送数据，等待响应
 * 3、关闭连接
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream sent(InputStream data);

    void close();
}
