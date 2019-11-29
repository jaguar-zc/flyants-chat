package io.sufeng.impl.websocket.handler;

import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.sufeng.impl.websocket.message.ErrorMsg;
import io.sufeng.impl.websocket.message.Msg;

/**
 * @description:
 * @author: sufeng
 * @create: 2019-11-22 17:06
 */
public abstract class AbstrctMessageHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        try{
            Msg msg1 = new Gson().fromJson(msg.toString(), Msg.class);
            onMessage(ctx,msg1);
        }catch (Exception e){
            ctx.writeAndFlush(new ErrorMsg(e.getMessage()));
        }
    }
    public abstract void onMessage(ChannelHandlerContext ctx,Msg t);
}
