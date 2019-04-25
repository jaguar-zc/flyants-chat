package org.flyants.authorize.service.impl;

import org.flyants.authorize.domain.repository.AuthorizeRepository;
import org.flyants.authorize.domain.repository.ClientRepository;
import org.flyants.authorize.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhangchao
 * @Date 2019/4/25 15:34
 * @Version v1.0
 */
@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Autowired
    private AuthorizeRepository authorizeRepository;


    @Autowired
    private ClientRepository clientRepository;


    @Override
    public boolean checkClientId(String clientId) {
        return false;
    }

    @Override
    public String authorizationCode() {


        return null;
    }

    @Override
    public boolean checkClientSecret(String clientSecret) {
        return false;
    }

    @Override
    public boolean checkRedirectUri(String redirectURI) {
        return false;
    }

    @Override
    public boolean checkAuthCode(String authCode) {
        return false;
    }
}
