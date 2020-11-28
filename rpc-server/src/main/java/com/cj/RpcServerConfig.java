package com.cj;

import com.cj.impl.HttpTransportServer;
import com.cj.impl.JsonDecoder;
import com.cj.impl.JsonEncoder;
import lombok.Data;

/**
 * Server 配置
 */
@Data
public class RpcServerConfig {
    // 指定网络模块
    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;
    // 指定序列化模块
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    // 指定默认端口
    private int port = 3000;
}
