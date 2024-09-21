package com.yuanyuan;

import com.yuanyuan.protocal.HttpServer;
import com.yuanyuan.register.LocalMap;

public class Provider {

    public static void main(String[] args) {
        // 传入一个映射， 一个接口名 -> 一个实现类
        LocalMap.register(HelloService.class.getName(), "1.0.0", HelloServiceImpl.class);
        LocalMap.register(HelloService.class.getName(), "2.0.0", HelloServiceImplNew.class);

        HttpServer server = new HttpServer();
        server.start("localhost", 8080);
    }

}
