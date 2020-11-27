package com.cj;

/**
 * 序列化：将对象转化为二进制数组
 */
public interface Encoder {
    /**
     * 编码：序列化
     * @param obj 需要编码的对象
     * @return 编码后的二进制数组
     */
    byte[] encode(Object obj);
}
