package com.cj;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) {
        // 使用默认配置创建客户端对象
        RpcClient client = new RpcClient(new RpcClientConfig());
        // 获取远程代理对象
        CalculateService service = client.getProxy(CalculateService.class);
        // 调用服务
        int add = service.add(3, 2);
        int minus = service.minus(3, 1);
        int mul = service.mul(3, 4);

        // 显示结果
        System.out.println("add=" + add);
        System.out.println("minus=" + minus);
        System.out.println("mul=" + mul);
    }
}
