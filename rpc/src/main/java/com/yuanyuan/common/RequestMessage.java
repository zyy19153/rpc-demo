package com.yuanyuan.common;

import java.io.Serializable;
import java.util.Arrays;

public class RequestMessage implements Serializable {
    private String interfaceName;
    private String methodName;
    private Class[] argsType;
    private Object[] args;
    private String version;

    public RequestMessage(String interfaceName, String methodName, Class[] argsType, Object[] args, String version) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.argsType = argsType;
        this.args = args;
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getArgsType() {
        return argsType;
    }

    public void setArgsType(Class[] argsType) {
        this.argsType = argsType;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "interfaceName='" + interfaceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", argsType=" + Arrays.toString(argsType) +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
