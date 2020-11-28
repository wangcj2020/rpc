package com.cj;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 用于调用远程服务的代理类
 */

@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;
    /**
     * 构造函数
     * @param clazz 远程服务类类型
     * @param encoder 序列化
     * @param decoder 反序列化
     * @param selector client选择器
     */
    public RemoteInvoker(Class clazz,
                         Encoder encoder,
                         Decoder decoder,
                         TransportSelector selector){
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }
    @Override
    public Object invoke(Object proxy,
                         Method method,
                         Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(this.clazz,method));
        request.setParameters(args);

        Response response = invokeRemote(request);
        if(response.getCode() != 0){
            // 响应出错，远程调用出错
            throw new IllegalStateException("fail to invoke remote: " + response);
        }
        return response.getData();
    }

    private Response invokeRemote(Request request) {
        Response response = null;
        TransportClient client = null;
        try {
            // 1、选择一个client
            client = selector.select();
            // 2、将Request序列化
            byte[] outBytes = encoder.encode(request);
            // 2.1、将请求信息发送到服务端,并等待响应数据(流)
            InputStream stream = client.sent(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(stream, stream.available());
            // 输出inBytes
//            int count = 0;
//            for (byte b:inBytes){
//                System.out.print(b);
//                System.out.print(',');
//                count++;
//                if(count >= 20){
//                    System.out.println();
//                    count=0;
//                }
//            }
            // 2.2、解码：反序列化
            response = decoder.decode(inBytes,Response.class); // 抛出异常
        }catch (IOException e) {
            log.warn(e.getMessage(),e);
            response = new Response();
            response.setCode(1);
            response.setMessage("RpcClient has a error:" + e.getClass().getName() + ":" + e.getMessage());
        }finally {
            if(client != null){
                selector.release(client);
            }
        }
        return response;
    }
}
