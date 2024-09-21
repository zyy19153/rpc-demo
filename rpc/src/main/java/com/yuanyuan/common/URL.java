package com.yuanyuan.common;

import java.io.Serializable;

public class URL implements Serializable {
    private String ip;
    private Integer port;

    public URL(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "URL{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
