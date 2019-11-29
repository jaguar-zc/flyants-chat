package io.sufeng.app.restful.context;

import io.sufeng.context.domain.entity.Token;
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

    @Override
    public Boolean checked(String token) {
        return tokenRepository.findByAccessToken(token).isPresent();
    }

    @Override
    public String getUserId(String token) {
        Optional<Token> tokenOptional = tokenRepository.findByAccessToken(token);
        return tokenOptional.get().getPeopleId();
    }

    @Override
    public void online(String token) {

    }

    @Override
    public void offline(String token) {

    }
}
