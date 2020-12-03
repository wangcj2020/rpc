package com.cj.impl;

import com.cj.Peer;
import com.cj.TransportClient;
import com.cj.TransportSelector;
import com.cj.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class RandomTransportSelector implements TransportSelector {
    /**
     * 已经连接好的client
     */
    private List<TransportClient> hasConn;

    public RandomTransportSelector(){
        hasConn = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peers,
                     int count,
                     Class<? extends TransportClient> clazz) {
        count = Math.max(count,1); //至少要建立一个连接
        for (Peer peer:peers){
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                hasConn.add(client);
            }
            log.info("connect server: {}",peer);
        }
    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(hasConn.size());
        return hasConn.remove(i);
    }

    @Override
    public synchronized void release(TransportClient client) {
        hasConn.add(client);
    }

    @Override
    public synchronized void close() {
        for (TransportClient client:hasConn){
            client.close();
        }
        hasConn.clear();
    }
}
