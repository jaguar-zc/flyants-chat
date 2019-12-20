package io.sufeng.impl.websocket.handler.def;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.sufeng.impl.websocket.GlobalChannelManager;
import io.sufeng.impl.websocket.ImUserService;
import io.sufeng.impl.websocket.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 鉴权
 *
 * @Author zhangchao
 * @Date 2019/3/26 15:50
 * @Version 1.0
 */
@Component
@ChannelHandler.Sharable
public class AuthorizeHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    public final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ImUserService imUserService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        Map<String, List<String>> attr = ctx.channel().attr(Config.URI_PARAMS).get();
        List<String> token = attr.get("token");
        if (token.size() == 0 || !imUserService.checked(token.get(0))) {
            logger.error("鉴权失败 Token错误 关闭连接:{}", token);
            ctx.channel().writeAndFlush(new TextWebSocketFrame("Token错误"));
            ctx.channel().close();
            return;
        }
        GlobalChannelManager.addTokenAndChannelId(token.get(0),ctx.channel().id().asLongText());
        logger.info("鉴权成功:{}", token);
        ctx.fireChannelRead(request.retain());
    }
}
