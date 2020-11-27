package com.cj.impl;

import com.alibaba.fastjson.JSON;
import com.cj.Decoder;

public class JsonDecoder implements Decoder{

    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
