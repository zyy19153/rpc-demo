package com.yuanyuan.protocal;

import com.yuanyuan.common.RequestMessage;
import com.yuanyuan.register.LocalMap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.http.*;

public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        // 处理请求 -> 接口、方法、方法传参
        try {
            RequestMessage request = (RequestMessage) new ObjectInputStream(req.getInputStream()).readObject();

            String interfaceName = request.getInterfaceName();
            Class implClass = LocalMap.get(interfaceName, request.getVersion());
            Method method = implClass.getMethod(request.getMethodName(), request.getArgsType());
            method.setAccessible(true);
            Object result = method.invoke(implClass.newInstance(), request.getArgs());

            OutputStream os = resp.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(result);
            oos.flush();
            oos.close();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }

    }
}
