package io.sufeng.impl.websocket.config;


import io.netty.util.AttributeKey;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author zhangchao
 * @Date 2019/3/21 13:08
 * @Version 1.0
 */
@Component
public class Config {

    public static final AttributeKey<Map<String, List<String>>> URI_PARAMS = AttributeKey.valueOf("URI_PARAMS") ;

    private String websocketUri;
    private int port;
    private int maxThreads;
    private int maxFrameLength;
    private int allIdelTime;

    public String getWebsocketUri() {
        return websocketUri;
    }

    public void setWebsocketUri(String websocketUri) {
        this.websocketUri = websocketUri;
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