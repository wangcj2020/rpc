package com.cj;

import com.cj.impl.HttpTransportClient;
import com.cj.impl.JsonDecoder;
import com.cj.impl.JsonEncoder;
import com.cj.impl.RandomTransportSelector;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class RpcClientConfig {
    // 指定网络模块
    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;
    // 指定序列化模块
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    // client选择
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1",3000));
}
