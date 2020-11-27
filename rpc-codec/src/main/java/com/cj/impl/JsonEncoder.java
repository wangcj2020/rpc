package com.cj.impl;

import com.alibaba.fastjson.JSON;
import com.cj.Encoder;

public class JsonEncoder implements Encoder {
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
