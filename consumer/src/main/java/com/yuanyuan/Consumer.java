package com.yuanyuan;

import com.yuanyuan.common.RequestMessage;
import com.yuanyuan.protocal.HttpClient;
import com.yuanyuan.proxy.ProxyFactory;

public class Consumer {

    public static void main(String[] args) {

        // 这是第一版的远程方法调用
/*
        RequestMessage message = new RequestMessage(HelloService.class.getName(),
                "hello",
                new Class[] {String.class},
                new String[] {"yuanyuan"},
                "2.0.0");

        HttpClient client = new HttpClient();

        String res = client.send("localhost", 8080, message);
        System.out.println(res);
*/

        HelloService service = ProxyFactory.getProxy(HelloService.class, "2.0.0");
        String res = service.hello("zhangyuanyuan");
        System.out.println(res);

    }

}
