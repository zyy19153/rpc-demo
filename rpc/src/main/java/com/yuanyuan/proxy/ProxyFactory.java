package com.yuanyuan.proxy;

import com.yuanyuan.common.RequestMessage;
import com.yuanyuan.common.URL;
import com.yuanyuan.loadbalance.RandomLoadBalance;
import com.yuanyuan.protocal.HttpClient;
import com.yuanyuan.register.RemoteRegisterMap;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

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

                // 服务发现
                List<URL> urls = RemoteRegisterMap.get(interfaceClass.getName());

                // 这里就可以实现负载均衡的策略
                URL url = RandomLoadBalance.random(urls);

                // 服务调用
                String res = client.send(url.getIp(), url.getPort(), message);
                return res;
            }
        });
        return (T) proxyInstance;
    }

}