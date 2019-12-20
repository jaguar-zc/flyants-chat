package io.sufeng.imimpl.netty;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.sufeng.common.utils.JsonUtils;
import io.sufeng.imimpl.netty.message.NettyMessage;

import java.util.Optional;

/**
 * @description:
 * @author: sufeng
 * @create: 2019-11-29 15:28
 */
public interface ImUserService {

    default Boolean checked(String token) {
        return false;
    }

    default String getMessageUserId(String token) {
        return "DEFAULT";
    }

    String getChannelIdByUserId(String userId) ;

    default void online(String host, String token) {
    }

    default void offline(String host, String token) {
    }

    default void sendMessage(String channelId, NettyMessage msg ){
        Optional<Channel> channel = GlobalChannelManager.findChannelByChannelId(channelId);
        if(channel.isPresent()){
            channel.get().writeAndFlush(new TextWebSocketFrame(JsonUtils.obj2json(msg)));
        }
    }

}
