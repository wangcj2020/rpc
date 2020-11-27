package com.cj.impl;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonDecoderTest extends TestCase {

    @Test
    public void testDecode() {
        // 1、编码：序列化
        JsonEncoder jsonEncoder = new JsonEncoder();
        TestBean bean = new TestBean();
        bean.setName("wang");
        bean.setAge(24);
        byte[] bytes = jsonEncoder.encode(bean);

        // 2、解码：反序列化
        JsonDecoder jsonDecoder = new JsonDecoder();
        TestBean decode = jsonDecoder.decode(bytes, TestBean.class);

        assertEquals(bean.getName(),decode.getName());
        assertEquals(bean.getAge(),decode.getAge());
    }
}