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

                // 假设 Provider 的服务仍未提供，可以进行 MOCK，方便 Consumer 开发
                // System.getProperty 就是从 VM arguments 中看有没有参数，可以添加一个 -Dmock=mock:mockData 试试
                String mock = System.getProperty("mock");
                if (mock != null && mock.startsWith("mock:")) {
                    return mock.replace("mock:", "");
                }

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

                // 服务容错的处理
                String res = null;
                int RETRY_COUNT = 10; // 重试次数
                while (RETRY_COUNT-- > 0) {
                    try {
                        // 服务调用
                        res = client.send(url.getIp(), url.getPort(), message);
                        break; // 成功就不用继续重试了。
                    } catch (Exception e) {
                        // 这里可以实现 error-callback 的机制
                        if(RETRY_COUNT == 0){
                            // 最后一次可以进行其他处理
                            res = "服务出错，联系开发人员xxx@xxx.com";
                        } else {
                            // 前面 9 次直接重试
                            e.printStackTrace();
                        }
                    }
                }
                return res;
            }
        });
        return (T) proxyInstance;
    }

}