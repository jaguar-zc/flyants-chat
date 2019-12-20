package io.sufeng.impl.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.sufeng.impl.websocket.config.Config;
import io.sufeng.impl.websocket.handler.def.AuthorizeHandler;
import io.sufeng.impl.websocket.handler.def.HttpRequestUriHandler;
import io.sufeng.impl.websocket.handler.RouterHandler;
import io.sufeng.impl.websocket.handler.def.ServerIdleStateTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangchao
 * @Date 2019/3/21 11:28
 * @Version 1.0
 */
@Configuration
public class NettyServer implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * NettyServerListener 日志输出器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    /**
     * 创建bootstrap
     */
    ServerBootstrap serverBootstrap ;
    /**
     * BOSS
     */
    EventLoopGroup boss = new NioEventLoopGroup();
    /**
     * Worker
     */
    EventLoopGroup work = new NioEventLoopGroup();

    /**
     * 通道适配器
     */
    @Autowired
    RouterHandler channelHandlerAdapter;

    /**
     * NETT服务器配置类
     */
    @Autowired
    Config config;

    /**
     * 运行中
     */
    Boolean isRun = false;


    @Autowired
    HttpRequestUriHandler httpRequestUriHandler;


    @Autowired
    AuthorizeHandler authorizeHandler;


    public ServerBootstrap getServerBootstrap(){
        if(serverBootstrap != null){
            return serverBootstrap;
        }

        if(boss == null){
            boss = new NioEventLoopGroup();
        }

        if(work == null){
            work = new NioEventLoopGroup();
        }

        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, work)
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, 100)
            .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    //心跳机制
                    pipeline.addLast("idleStateHandler", new IdleStateHandler(0,0, config.getAllIdelTime(),TimeUnit.SECONDS));
                    //处理心跳事件
                    pipeline.addLast("serverIdleStateTrigger", new ServerIdleStateTrigger());
                    pipeline.addLast("httpCodec", new HttpServerCodec());
                    pipeline.addLast("aggregator", new HttpObjectAggregator(config.getMaxFrameLength()));
                    pipeline.addLast("httpChunked", new ChunkedWriteHandler());
                    pipeline.addLast("httpRequestHandler", httpRequestUriHandler);
                    pipeline.addLast("authorizeHandler", authorizeHandler);
                    pipeline.addLast("webSocketServerProtocolHandler", new WebSocketServerProtocolHandler(config.getWebsocketUri()));
                    pipeline.addLast(channelHandlerAdapter);
                }
            });
        return serverBootstrap;
    }


    /**
     * 开启及服务线程
     */
    @Async
    public void start() {
        if(isRun)return;
        isRun = true;
        // 从配置文件中(application.yml)获取服务端监听端口号
        int port = config.getPort();
        try {
            LOGGER.info("Netty服务器在[{}]端口启动监听 {}", port,config.getWebsocketUri());
            ChannelFuture f = getServerBootstrap().bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            close();
        }
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                start();
            }
        }).start();
    }

    /**
     * 关闭服务器方法
     */
    @PreDestroy
    public void close() {
        LOGGER.info("关闭服务器....");
        //优雅退出
        boss.shutdownGracefully();
        work.shutdownGracefully();
        serverBootstrap = null;
        boss = null;
        work = null;
        isRun = false;
    }

}
