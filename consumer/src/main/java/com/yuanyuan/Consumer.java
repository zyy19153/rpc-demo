package com.yuanyuan;

import com.yuanyuan.common.RequestMessage;
import com.yuanyuan.protocal.HttpClient;

public class Consumer {

    public static void main(String[] args) {

        RequestMessage message = new RequestMessage(HelloService.class.getName(),
                "hello",
                new Class[] {String.class},
                new String[] {"yuanyuan"},
                "1.0.0");

        HttpClient client = new HttpClient();

        String res = client.send("localhost", 8080, message);
        System.out.println(res);

    }

}
