package io.sufeng.context.domain.service;

import io.sufeng.context.domain.entity.message.MessageServer;

import java.util.List;

/**
 * @author: sufeng
 * @create: 2019-12-20 16:11
 */
public interface MessageServerService {

    List<MessageServer> listAll();

    void start(String host,int port);

    void stop(String host,int port);

}
