package io.sufeng.imimpl.netty.config;


import io.netty.util.AttributeKey;

import java.util.List;
import java.util.Map;

/**
 * @Author zhangchao
 * @Date 2019/3/21 13:08
 * @Version 1.0
 */
public class Config {
    public static final String DEFAULT_API_GATEWAY = "/app/even/if/the/communication/gateway";

    public static final AttributeKey<Map<String, List<String>>> URI_PARAMS = AttributeKey.valueOf("URI_PARAMS") ;

    private String apiGateway = DEFAULT_API_GATEWAY;
    private int port = 7002;
    private int maxThreads = 100;
    private int maxFrameLength = 65535;
    private int allIdelTime = 30;

    public Config(int port) {
        this.port = port;
    }

    public String getApiGateway() {
        return apiGateway;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public int getMaxFrameLength() {
        return maxFrameLength;
    }

    public void setMaxFrameLength(int maxFrameLength) {
        this.maxFrameLength = maxFrameLength;
    }

    public int getAllIdelTime() {
        return allIdelTime;
    }

    public void setAllIdelTime(int allIdelTime) {
        this.allIdelTime = allIdelTime;
    }
}