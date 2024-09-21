package com.yuanyuan.loadbalance;

import com.yuanyuan.common.URL;

import java.util.List;
import java.util.Random;

// todo 这里可以实现其他的负载均衡策略
public class RandomLoadBalance {

    public static URL random(List<URL> urls) {
        Random r = new Random();
        int i = r.nextInt(urls.size());
        return urls.get(i);
    }
}
