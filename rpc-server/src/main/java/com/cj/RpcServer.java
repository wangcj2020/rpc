package com.cj;

import com.cj.common.utils.ReflectionUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * rpc Server核心类
 */
@Data
@Slf4j
public class RpcServer {
    // 服务配置
    private RpcServerConfig config;
    // 网络传输
    private TransportServer net;
    // 序列化与反序列haul
    private Encoder encoder;
    private Decoder decoder;
    // 服务管理与服务调用
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream fromRequest, OutputStream toResponse) {
            Response response = new Response();
            try {
                // 获取二进制数组
                byte[] inBytes = IOUtils.readFully(fromRequest, fromRequest.available());
                // 解码：反序列化
                Request request = decoder.decode(inBytes, Request.class);
                log.info("get request: {}",request);
                ServiceInstance serviceInstance = serviceManager.lookup(request);
                Object res = serviceInvoker.invoke(serviceInstance, request);
                // 设置响应
                response.setData(res);
            } catch (Exception e) {
                log.warn(e.getMessage(),e);
                response.setCode(1);
                response.setMessage("RpcServer has a error:" + e.getClass().getName() + ":" + e.getMessage());
            } finally {
                // 编码：序列化
                byte[] outBytes = encoder.encode(response);
                try {
                    // 返回响应
                    toResponse.write(outBytes);

                    log.info("response client...");
                } catch (IOException e) {
                    log.warn(e.getMessage(),e);
                }
            }
        }
    };

    public RpcServer(RpcServerConfig config){
        this.config = config;

        // 用反射根据配置信息生成net、encoder与decoder
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(),this.handler);

        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        // 初始化服务管理与服务调用
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean){
        serviceManager.register(interfaceClass,bean);
    }

    public void start(){
        this.net.start();
    }

    public  void stop(){
        this.net.stop();
    }
}
