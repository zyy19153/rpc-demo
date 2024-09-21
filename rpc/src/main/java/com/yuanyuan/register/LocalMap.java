package com.yuanyuan.register;

import java.util.HashMap;
import java.util.Map;

public class LocalMap {

    private static Map<String, Class> map = new HashMap<>();

    public static void register(String key, String version, Class value) {
        map.put(key + version, value);
    }

    public static Class get(String key, String version) {
        return map.get(key + version);
    }

}
