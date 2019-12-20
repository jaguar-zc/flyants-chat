package io.sufeng.app.restful.context;

import io.sufeng.context.domain.entity.Token;
import io.sufeng.context.domain.entity.message.MessageUser;
import io.sufeng.context.domain.repository.MessageUserRepository;
import io.sufeng.context.domain.repository.TokenRepository;
import io.sufeng.impl.websocket.ImUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @description:
 * @author: sufeng
 * @create: 2019-11-29 15:41
 */
@Component
public class ImUserServiceImpl implements ImUserService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    MessageUserRepository messageUserRepository;

    @Override
    public Boolean checked(String token) {
        return tokenRepository.findByAccessToken(token).isPresent();
    }

    @Override
    public String getMessageUserId(String token) {
        Optional<Token> tokenOptional = tokenRepository.findByAccessToken(token);
        MessageUser messageUser = messageUserRepository.findByPeopleId(tokenOptional.get().getPeopleId());
        return messageUser.getId();
    }

    @Override
    public void online(String host,String token) {
        Optional<Token> tokenOptional = tokenRepository.findByAccessToken(token);
        MessageUser messageUser = messageUserRepository.findByPeopleId(tokenOptional.get().getPeopleId());
        messageUser.setStatus(MessageUser.Status.ONLINE);
        messageUser.setHost(host);
        messageUserRepository.saveAndFlush(messageUser);
    }

    @Override
    public void offline(String host,String token) {
        Optional<Token> tokenOptional = tokenRepository.findByAccessToken(token);
        MessageUser messageUser = messageUserRepository.findByPeopleId(tokenOptional.get().getPeopleId());
        messageUser.setStatus(MessageUser.Status.OFFLINE);
        messageUser.setHost(host);
        messageUserRepository.saveAndFlush(messageUser);
    }
}
