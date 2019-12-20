package io.sufeng.imimpl.netty.handler;

import io.netty.channel.ChannelHandlerContext;

/**
 * @description:
 * @author: sufeng
 * @create: 2019-12-03 16:26
 */
public interface MessageDecoderErrorHandler {

    default boolean onErrorMessage(ChannelHandlerContext ctx, String msg){
       System.out.println(String.format("onErrorMessage : %s",msg));
       return false;
    };

}
