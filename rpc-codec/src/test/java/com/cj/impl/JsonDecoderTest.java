package com.cj.impl;

import com.cj.Response;
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

    @Test
    public void testDecodeBytes(){
        JsonDecoder jsonDecoder = new JsonDecoder();
        byte[] bytes = new byte[]{60,104,116,109,108,62,10,60,104,101,97,100,62,10,60,109,101,116,97,32,
                104,116,116,112,45,101,113,117,105,118,61,34,67,111,110,116,101,110,116,45,
                84,121,112,101,34,32,99,111,110,116,101,110,116,61,34,116,101,120,116,47,
                104,116,109,108,59,99,104,97,114,115,101,116,61,117,116,102,45,56,34,47,
                62,10,60,116,105,116,108,101,62,69,114,114,111,114,32,52,48,52,32,78,
                111,116,32,70,111,117,110,100,60,47,116,105,116,108,101,62,10,60,47,104,
                101,97,100,62,10,60,98,111,100,121,62,60,104,50,62,72,84,84,80,32,
                69,82,82,79,82,32,52,48,52,60,47,104,50,62,10,60,112,62,80,114,
                111,98,108,101,109,32,97,99,99,101,115,115,105,110,103,32,47,46,32,82,
                101,97,115,111,110,58,10,60,112,114,101,62,32,32,32,32,78,111,116,32,
                70,111,117,110,100,60,47,112,114,101,62,60,47,112,62,60,104,114,62,60,
                97,32,104,114,101,102,61,34,104,116,116,112,58,47,47,101,99,108,105,112,
                115,101,46,111,114,103,47,106,101,116,116,121,34,62,80,111,119,101,114,101,
                100,32,98,121,32,74,101,116,116,121,58,47,47,32,57,46,52,46,54,46,
                118,50,48,49,55,48,53,51,49,60,47,97,62,60,104,114,47,62,10,10,
                60,47,98,111,100,121,62,10,60,47,104,116,109,108,62,10};
        Response response = jsonDecoder.decode(bytes, Response.class);
        System.out.println(response);
    }
}