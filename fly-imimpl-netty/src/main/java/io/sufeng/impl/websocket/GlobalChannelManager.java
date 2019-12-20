package io.sufeng.impl.websocket;

import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.sufeng.impl.websocket.message.Msg;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @Author zhangchao
 * @Date 2019/3/21 16:39
 * @Version 1.0
 */
public class GlobalChannelManager {

    private static Map<String,String> tokenChannelIdMaps = new ConcurrentHashMap<String,String>();
    private static Map<String,Channel> channelIdChannelMaps = new ConcurrentHashMap<String,Channel>();


    public static void addTokenAndChannelId(String token,String channelId){
        tokenChannelIdMaps.put(token,channelId);
    }

    public static String findChannelIdByToken(String token){
        return tokenChannelIdMaps.get(token);
    }

    public static void addChannelIdAndChannel(String channelId,Channel channel){
        channelIdChannelMaps.put(channelId,channel);
    }

    public static Channel findChannelByChannelId(String channelId){
        return channelIdChannelMaps.get(channelId);
    }

    public static void remove(String channelId){

        tokenChannelIdMaps.forEach((k,v) ->{
            if(v.equals(channelId)){
                tokenChannelIdMaps.remove(k);
            }
        });

        channelIdChannelMaps.remove(channelId);
    }


//    public static void publisher(String userId,Msg msg){
//        Channel channel = userChannelMap.get(userId);
//        channel.writeAndFlush(new TextWebSocketFrame(new Gson().toJson(msg)));
//    }


}
