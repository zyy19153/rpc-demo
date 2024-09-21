package com.yuanyuan.register;

import com.yuanyuan.common.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteRegisterMap {
    /*
    注册中心的实现三要素
    1. 可以共享数据
    2. 心跳机制，可以检测对应机器是否存活
    3. 数据变更的监听和更新机制
     */

    private static Map<String, List<URL>> map = new HashMap<>();

    public static void register(String interfaceName, URL url) {
        map.computeIfAbsent(interfaceName, k -> new ArrayList<>()).add(url);
        saveFile();
    }

    public static List<URL> get(String interfaceName) {
        map = getFile();
        return map.get(interfaceName);
    }

    public static void saveFile() {
        try {
            FileOutputStream fos = new FileOutputStream("/temp.text");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fis = new FileInputStream("/temp.text");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<String, List<URL>> res = (Map<String, List<URL>>) ois.readObject();
            ois.close();
            return res;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
