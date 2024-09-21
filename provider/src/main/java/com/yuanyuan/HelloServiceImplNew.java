package com.yuanyuan;

public class HelloServiceImplNew implements HelloService {
    @Override
    public String hello(String name) {
        return "new hello " + name;
    }
}
