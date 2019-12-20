package io.sufeng.impl.websocket.handler;

import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.sufeng.impl.websocket.message.Msg;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @description:
 * @author: sufeng
 * @create: 2019-11-22 17:06
 */
public abstract class AbstrctMessageHandler extends ChannelInboundHandlerAdapter implements MessageHandler {

    Iterator<MessageHandler>  messageHandlerServiceLoader;
    Iterator<MessageDecoderErrorHandler> messageDecoderErrorHandlerIterator;
    {
        messageHandlerServiceLoader = ServiceLoader.load(MessageHandler.class).iterator();
        messageDecoderErrorHandlerIterator = ServiceLoader.load(MessageDecoderErrorHandler.class).iterator();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        try{
            while(messageHandlerServiceLoader.hasNext()) {
                MessageHandler messageHandler = messageHandlerServiceLoader.next();
                Msg msg1 = new Gson().fromJson(msg.toString(), Msg.class);
                boolean result = messageHandler.onMessage(ctx, msg1);
                if(result){
                    break;
                }
            }
        }catch (Exception e){
            while (messageDecoderErrorHandlerIterator.hasNext()){
                messageDecoderErrorHandlerIterator.next().onErrorMessage(ctx,msg.toString());
            }
        }
    }
}
