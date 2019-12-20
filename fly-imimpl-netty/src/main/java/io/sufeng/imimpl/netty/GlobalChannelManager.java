package io.sufeng.imimpl.netty;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

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

    public static Optional<String> findChannelIdByToken(String token){
        return Optional.ofNullable(tokenChannelIdMaps.get(token));
    }

    public static void addChannelIdAndChannel(String channelId,Channel channel){
        channelIdChannelMaps.put(channelId,channel);
    }

    public static Optional<Channel> findChannelByChannelId(String channelId){
        return Optional.ofNullable(channelIdChannelMaps.get(channelId));
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
