package com.cj.impl;

import com.cj.Peer;
import com.cj.TransportClient;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTransportClient implements TransportClient {
    private String url;

    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
        System.out.println(url);
    }

    @Override
    public InputStream sent(InputStream data) {
        try {
            // 获取连接
            HttpURLConnection httpConn = (HttpURLConnection) new URL(url).openConnection();
            // 设置连接
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");

            httpConn.connect(); // 连接
            // 将数据发送到连接的output中
            IOUtils.copy(data, httpConn.getOutputStream());

            int responseCode = httpConn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                return httpConn.getInputStream();
            }else{
                String responseMessage = httpConn.getResponseMessage();
                System.out.println(responseMessage);
                return httpConn.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
