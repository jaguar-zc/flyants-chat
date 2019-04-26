package org.flyants.authorize.domain.service.impl;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.flyants.authorize.domain.repository.OAuthAccessTokenRepository;
import org.flyants.authorize.domain.repository.OAuthorizeRequestRepository;
import org.flyants.authorize.domain.repository.ClientRepository;
import org.flyants.authorize.domain.repository.OAuthUserAuthorizeRepository;
import org.flyants.authorize.domain.service.AuthorizeService;
import org.flyants.authorize.oauth2.OAuthAccessToken;
import org.flyants.authorize.oauth2.OAuthAuthorizeRequest;
import org.flyants.authorize.oauth2.OAuthClient;
import org.flyants.authorize.oauth2.OAuthUserAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/4/25 15:34
 * @Version v1.0
 */
@Transactional
@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Autowired
    private OAuthorizeRequestRepository authorizeRequestRepository;

    @Autowired
    private OAuthUserAuthorizeRepository oAuthUserAuthorizeRepository;

    @Autowired
    private OAuthAccessTokenRepository oAuthAccessTokenRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public boolean checkClientId(String clientId) {
        return clientRepository.findById(clientId).isPresent();
    }

    @Override
    public OAuthClient findOAuthClinetByClientId(String clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("clientId不存在"));
    }

    @Override
    public OAuthUserAuthorize findOauthUserAuthorizeByClientIdAndUserId(String clientId, Long userId) {
        return oAuthUserAuthorizeRepository.findByClientIdAndUserId(clientId,userId).orElseThrow(() -> new RuntimeException("用户不存在"));
    }

    @Override
    public boolean checkClientSecret(String clientId,String clientSecret) {
        return clientRepository.findByClientIdAndClientSecret(clientId,clientSecret).isPresent();
    }

    @Override
    public boolean checkRedirectUri(String clientId,String redirectURI) {
        return clientRepository.findByClientIdAndClientRedirectUriHost(clientId,clientId).isPresent();
    }

    @Override
    public boolean checkAuthCode(String authCode) {
        return authorizeRequestRepository.findByAuthorizationCode(authCode).isPresent();
    }

    @Override
    public OAuthAuthorizeRequest authorization(String response_type, String client_id, String redirect_uri, String scope, String state) {

        OAuthIssuerImpl authIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

        OAuthAuthorizeRequest request = new OAuthAuthorizeRequest();
        request.setCreationDate(new Date());
        request.setModificationDate(new Date());
        request.setAuthState(2);
        try {
            request.setAuthorizationCode(authIssuerImpl.authorizationCode());
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }
        request.setRedirectUri(redirect_uri);
        request.setResponseType(response_type);
        request.setState(state);
        request.setClientId(client_id);
        authorizeRequestRepository.saveAndFlush(request);

        return request;
    }






    @Override
    public boolean checkAccessToken(String accessToken) {
        return oAuthAccessTokenRepository.findByToken(accessToken).isPresent();
    }

    @Override
    public Optional<Long> findPeopleIdByOpenId(String accessToken, String openId) {
        Optional<OAuthAccessToken> optionalOAuthAccessToken = oAuthAccessTokenRepository.findByToken(accessToken);
        if(optionalOAuthAccessToken.isPresent()){
            String clientId = optionalOAuthAccessToken.get().getClientId();

            Optional<OAuthUserAuthorize> oAuthUserAuthorize = oAuthUserAuthorizeRepository.findByClientIdAndOauthUserId(clientId, openId);
            return Optional.of(oAuthUserAuthorize.get().getUserId());
        }
        return Optional.empty();
    }
}
