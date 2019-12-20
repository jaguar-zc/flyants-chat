package io.sufeng.imimpl.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.sufeng.imimpl.netty.message.NettyMessage;

/**
 * @description:
 * @author: sufeng
 * @create: 2019-12-03 16:26
 */
public interface MessageHandler {

    boolean onMessage(ChannelHandlerContext ctx, NettyMessage t);

    default boolean onErrorMessage(ChannelHandlerContext ctx, String msg){
       System.out.println(String.format("onErrorMessage : %s",msg));
       return false;
    };

}
