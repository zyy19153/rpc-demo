package com.yuanyuan;

import com.yuanyuan.common.URL;
import com.yuanyuan.protocal.HttpServer;
import com.yuanyuan.register.LocalMap;
import com.yuanyuan.register.RemoteRegisterMap;

public class Provider {

    public static void main(String[] args) {
        // 传入一个映射， 一个接口名 -> 一个实现类
        // 本地方法注册
        LocalMap.register(HelloService.class.getName(), "1.0.0", HelloServiceImpl.class);
        LocalMap.register(HelloService.class.getName(), "2.0.0", HelloServiceImplNew.class);

        // 远程服务注册
        // TODO 这里其实可以把 ip 和 port 写成配置的方式，而不是硬编码
        URL url = new URL("localhost", 8080);
        RemoteRegisterMap.register(HelloService.class.getName(), url);

        HttpServer server = new HttpServer();
        server.start(url.getIp(), url.getPort());
    }

}
