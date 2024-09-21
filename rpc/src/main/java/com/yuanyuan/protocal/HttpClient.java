package com.yuanyuan.protocal;

import com.yuanyuan.common.RequestMessage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpClient {

    public String send(String hostname, Integer port, RequestMessage message) {
        // 这里可以读取用户的配置（例如使用什么发送用户的请求）
        try {

            // 域名、端口
            URL url = new URL("http", hostname, port, "/");
            // 建立链接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置需要发送的方式
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // 配置
            OutputStream os = connection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(message);
            oos.flush();
            oos.close();

            InputStream is = connection.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            String result = (String) ois.readObject();
            return result;


        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
