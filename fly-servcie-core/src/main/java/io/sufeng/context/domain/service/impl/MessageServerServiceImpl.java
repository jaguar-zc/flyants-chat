package io.sufeng.context.domain.service.impl;

import io.sufeng.context.domain.entity.message.MessageServer;
import io.sufeng.context.domain.repository.MessageServerRepository;
import io.sufeng.context.domain.service.MessageServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author: sufeng
 * @create: 2019-12-20 16:13
 */
@Service
public class MessageServerServiceImpl implements MessageServerService {

    @Autowired
    MessageServerRepository messageServerRepository;

    @Override
    public List<MessageServer> listAll() {
        return messageServerRepository.findAll();
    }

    @Override
    public void start(String host, int port) {

        Optional<MessageServer> optionalMessageServer =  messageServerRepository.findByHostAndPort(host,port);
        if(!optionalMessageServer.isPresent()){
            MessageServer messageServer = new MessageServer();
            messageServer.setServerStatus(MessageServer.ServerStatus.STOP);
            messageServer.setHost(host);
            messageServer.setPort(port);
            messageServer.setCreateTime(new Date());
            messageServer.setUpdateTime(new Date());
            messageServerRepository.saveAndFlush(messageServer);
            optionalMessageServer = Optional.of(messageServer);
        }

        MessageServer messageServer = optionalMessageServer.get();
        messageServer.setUpdateTime(new Date());
        messageServer.setServerStatus(MessageServer.ServerStatus.START);
        messageServerRepository.saveAndFlush(messageServer);
    }

    @Override
    public void stop(String host, int port) {
        Optional<MessageServer> optionalMessageServer =  messageServerRepository.findByHostAndPort(host,port);
        if(!optionalMessageServer.isPresent()){
            MessageServer messageServer = new MessageServer();
            messageServer.setServerStatus(MessageServer.ServerStatus.STOP);
            messageServer.setHost(host);
            messageServer.setPort(port);
            messageServer.setCreateTime(new Date());
            messageServer.setUpdateTime(new Date());
            messageServerRepository.saveAndFlush(messageServer);
            optionalMessageServer = Optional.of(messageServer);
        }

        MessageServer messageServer = optionalMessageServer.get();
        messageServer.setUpdateTime(new Date());
        messageServer.setServerStatus(MessageServer.ServerStatus.STOP);
        messageServerRepository.saveAndFlush(messageServer);
    }
}
