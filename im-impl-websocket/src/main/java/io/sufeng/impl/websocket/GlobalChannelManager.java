package io.sufeng.impl.websocket;

import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.sufeng.impl.websocket.message.Msg;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author zhangchao
 * @Date 2019/3/21 16:39
 * @Version 1.0
 */
public class GlobalChannelManager {

    private static Map<String,Channel> userChannelMap = new ConcurrentHashMap<>();
    private static Map<Channel,String> channeUserlMap = new ConcurrentHashMap<>();


    public static void add(String userId,Channel channel){
        userChannelMap.put(userId,channel);
        channeUserlMap.put(channel,userId);
    }


    public static void remove(Channel channel){
        String userId = channeUserlMap.get(channel);
        userChannelMap.remove(userId);
        channeUserlMap.remove(channel);
    }


    public static void publicsh(String userId,Msg msg){
        Channel channel = userChannelMap.get(userId);
        channel.writeAndFlush(new TextWebSocketFrame(new Gson().toJson(msg)));
    }

}
