package com.cj;

/**
 * 反序列化：将二进制数组转化为指定对象
 */
public interface Decoder {
    /**
     * 解码：反序列化
     * @param bytes 需要解码的二进制数组
     * @param clazz 解码生成的对象的类对象
     * @param <T> 通过clazz确定解码后的对象类型
     * @return
     */
    <T> T decode(byte[] bytes,Class<T> clazz);
}
