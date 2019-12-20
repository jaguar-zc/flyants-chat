package io.sufeng.imimpl.netty.handler;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import io.sufeng.common.utils.JsonUtils;
import io.sufeng.imimpl.netty.GlobalChannelManager;
import io.sufeng.imimpl.netty.ImUserService;
import io.sufeng.imimpl.netty.message.ErrorMsg;
import io.sufeng.imimpl.netty.message.NettyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Author zhangchao
 * @Date 2019/3/21 11:29
 * @Version 1.0
 */
@Component
@ChannelHandler.Sharable
public class RouterHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger logger = LoggerFactory.getLogger(RouterHandler.class.getName());
    private WebSocketServerHandshaker handshaker;


    @Autowired
    ImUserService imUserService;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 传统的HTTP接入
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }
        // WebSocket接入
        else if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {

        // 如果HTTP解码失败，返回HHTP异常
        if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST));
            return;
        }
        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8088", null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {

        // 判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否是Ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 本例程仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(
                    String.format("%s frame types not supported", frame.getClass().getName()));
        }

        // 返回应答消息
//        String request = ((TextWebSocketFrame) frame).text();
        logger.info("{}",((TextWebSocketFrame) frame).text());

        try{
            String messageString = ((TextWebSocketFrame) frame).text();
            NettyMessage message = JsonUtils.json2pojo(messageString, NettyMessage.class);
            logger.info("{}",message);
            String toChannelId = imUserService.getChannelIdByUserId(message.getToId());
            imUserService.sendMessage(toChannelId,message);
        }catch (Exception e){
            logger.error("{}",e.getMessage());
            ctx.writeAndFlush(new TextWebSocketFrame(JsonUtils.obj2json(new ErrorMsg(e.getMessage()))));
        }

    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        // 返回应答给客户端
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(res, res.content().readableBytes());
        }

        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("exceptionCaught");
        cause.printStackTrace();
        ctx.close();
        GlobalChannelManager.remove(ctx.channel().id().asLongText());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        logger.info("channelActive");
        String channelId = ctx.channel().id().asLongText();
        GlobalChannelManager.addChannelIdAndChannel(channelId,ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        logger.info("channelInactive");
        GlobalChannelManager.remove(ctx.channel().id().asLongText());
    }
}
