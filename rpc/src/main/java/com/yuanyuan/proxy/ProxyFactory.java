package com.yuanyuan.proxy;

import com.yuanyuan.common.RequestMessage;
import com.yuanyuan.protocal.HttpClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static <T> T getProxy(Class<T> interfaceClass, String version) {
        // 使用 jdk 的动态代理
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] {interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                RequestMessage message = new RequestMessage(interfaceClass.getName(),
                        method.getName(),
                        method.getParameterTypes(),
                        args,
                        version);

                HttpClient client = new HttpClient();

                String res = client.send("localhost", 8080, message);
                return res;
            }
        });
        return (T) proxyInstance;
    }

}