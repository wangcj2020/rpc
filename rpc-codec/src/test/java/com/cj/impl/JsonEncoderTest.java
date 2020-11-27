package com.cj.impl;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonEncoderTest extends TestCase {

    @Test
    public void testEncode() {
        JsonEncoder jsonEncoder = new JsonEncoder();
        TestBean bean = new TestBean();
        bean.setName("wang");
        bean.setAge(24);
        byte[] bytes = jsonEncoder.encode(bean);

        assertNotNull(bytes);
    }
}