package com.cj;

import java.util.List;

/**
 * 服务端连接选择器：选择连接哪一个服务端
 */
public interface TransportSelector {
    /**
     * 初始化
     * @param peers 允许连接的所有服务端端点信息
     * @param count client与server之间建立的连接数目
     * @param clazz client的类类型
     */
    void init(List<Peer> peers,
              int count,
              Class<? extends TransportClient> clazz);
    /**
     * 选择一个TransportClient与服务端进行交互
     * @return 网络client
     */
    TransportClient select();

    /**
     * 释放用完的client
     * @param client 用完的client
     */
    void release(TransportClient client);

    /**
     * 关闭TransportSelector
     */
    void close();
}
