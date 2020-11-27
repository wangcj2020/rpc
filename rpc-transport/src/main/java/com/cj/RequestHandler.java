package com.cj;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理请求：根据实际情况使用时实现
 */
public interface RequestHandler {
    void onRequest(InputStream fromRequest, OutputStream toResponse);
}
