package com.cj;

import com.cj.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * rpc Client核心类
 */
public class RpcClient {
    // 客户端配置
    private RpcClientConfig config;
    // 序列化与反序列化
    private Encoder encoder;
    private Decoder decoder;
    // 选择器
    private TransportSelector selector;

    public RpcClient(){
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config){
        this.config = config;

        // 用反射根据配置信息生成encoder、decoder及选择器
        this.encoder = ReflectionUtils.newInstance(this.config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(this.config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(this.config.getSelectorClass());

        this.selector.init(
                this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass()
        );
    }

    /**
     * 获取clazz接口子类的一个动态代理对象
     * @param clazz 接口
     * @param <T> 接口子类
     * @return 动态代理对象
     */
    public <T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[] {clazz},
                new RemoteInvoker(clazz,encoder,decoder,selector)
        );
    }
}
