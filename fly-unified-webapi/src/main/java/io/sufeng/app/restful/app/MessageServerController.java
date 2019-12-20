package io.sufeng.app.restful.app;

import io.sufeng.common.annotation.Anonymous;
import io.sufeng.context.configuration.PageResult;
import io.sufeng.context.domain.entity.comments.CommentsType;
import io.sufeng.context.domain.entity.message.MessageServer;
import io.sufeng.context.domain.service.CommentsService;
import io.sufeng.context.domain.service.MessageServerService;
import io.sufeng.context.dto.app.CommentsPublishDto;
import io.sufeng.context.dto.app.CommentsSimpleDto;
import io.sufeng.context.utils.JWTManager;
import io.sufeng.imimpl.netty.NettyServer;
import io.sufeng.imimpl.netty.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/5/28 10:05
 * @Version v1.0
 */
@RestController
@RequestMapping(AppVersion.version+"/message/server")
@Slf4j

public class MessageServerController {

    @Autowired
    MessageServerService messageServerService;


    @Autowired
    NettyServer nettyServer;

    @Anonymous
    @PostMapping("/start")
    public void start(String host,int port){

        new Thread(new Runnable() {
            @Override
            public void run() {
                nettyServer.start(new Config(port));
            }
        }).start();
        messageServerService.start(host,port);
    }

    @Anonymous
    @PostMapping("/stop")
    public void stop(String host,int port){
                nettyServer.close();
        messageServerService.stop(host,port);
    }

    @Anonymous
    @GetMapping("/list")
    public List<MessageServer> listAll( ) {
        return messageServerService.listAll();
    }

}
