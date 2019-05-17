package org.flyants.authorize.domain.service.impl;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.issuer.ValueGenerator;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.flyants.authorize.domain.entity.oauth2.OAuthAccessToken;
import org.flyants.authorize.domain.entity.oauth2.OAuthAuthorizeRequest;
import org.flyants.authorize.domain.entity.oauth2.OAuthClient;
import org.flyants.authorize.domain.entity.oauth2.OAuthUserAuthorize;
import org.flyants.authorize.domain.entity.platform.People;
import org.flyants.authorize.domain.repository.ClientRepository;
import org.flyants.authorize.domain.repository.OAuthAccessTokenRepository;
import org.flyants.authorize.domain.repository.OAuthUserAuthorizeRepository;
import org.flyants.authorize.domain.repository.OAuthorizeRequestRepository;
import org.flyants.authorize.domain.service.AuthorizeService;
import org.flyants.authorize.utils.ResourceUtils;
import org.flyants.common.exception.BusinessException;
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


    private ValueGenerator generator = new MD5Generator();

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
        return clientRepository.findById(clientId).orElseThrow(() -> new BusinessException("clientId不存在"));
    }

    @Override
    public OAuthUserAuthorize findOauthUserAuthorizeByClientIdAndUserId(String clientId, Long userId) {
        return oAuthUserAuthorizeRepository.findByClientIdAndUserId(clientId,userId).orElseThrow(() -> new BusinessException("用户不存在"));
    }

    @Override
    public boolean checkClientSecret(String clientId,String clientSecret) {
        return clientRepository.findByClientIdAndClientSecret(clientId,clientSecret).isPresent();
    }

    @Override
    public boolean checkRedirectUri(String clientId,String redirectURI) {
        return clientRepository.findByClientIdAndClientRedirectUriHost(clientId,redirectURI).isPresent();
    }

    @Override
    public boolean checkAuthCode(String authCode) {
        Optional<OAuthAuthorizeRequest> oAuthAuthorizeRequest = authorizeRequestRepository.findByAuthorizationCode(authCode)
                .filter(i -> i.getAuthState() == 2)
                .filter(i -> i.getStatus() == 0);

        return oAuthAuthorizeRequest.isPresent();
    }

    @Override
    public OAuthAuthorizeRequest authorization(String response_type, String client_id, String redirect_uri, String scope, String state) {


        People currentPeople = ResourceUtils.getCurrentPeople();

        Optional<OAuthClient> client = clientRepository.findById(client_id);

        OAuthAuthorizeRequest request = new OAuthAuthorizeRequest();
        request.setCreationDate(new Date());
        request.setModificationDate(new Date());
        request.setStatus(0);
        request.setAuthState(2);
        try {
            request.setAuthorizationCode(new OAuthIssuerImpl(generator).authorizationCode());
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }
        request.setUserId(currentPeople.getId());
        request.setRedirectUri(redirect_uri);
        request.setResponseType(response_type);
        request.setState(state);
        request.setClientId(client_id);
        authorizeRequestRepository.saveAndFlush(request);


        Optional<OAuthUserAuthorize> oAuthUserAuthorize = oAuthUserAuthorizeRepository.findByClientIdAndUserId(client_id, currentPeople.getId());

        if(!oAuthUserAuthorize.isPresent()){
            OAuthUserAuthorize oAuthUserAuthorize1 = oAuthUserAuthorize.orElseGet(() -> {
                OAuthUserAuthorize userAuthorize = new OAuthUserAuthorize();
                userAuthorize.setClientId(client_id);
                try {
                    userAuthorize.setOauthUserId(new OAuthIssuerImpl(generator).authorizationCode());
                } catch (OAuthSystemException e) {
                    e.printStackTrace();
                }
                userAuthorize.setUserId(currentPeople.getId());
                userAuthorize.setOauthUserName(currentPeople.getUsername());
                userAuthorize.setAuthorizeResource(client.get().getOAuthClientResource().getResource());
                return userAuthorize;
            });
            oAuthUserAuthorizeRepository.saveAndFlush(oAuthUserAuthorize1);
        }

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
            oAuthUserAuthorize.orElseThrow(()->new BusinessException("error openId!"));
            return Optional.of(oAuthUserAuthorize.get().getUserId());
        }
        return Optional.empty();
    }

    @Override
    public OAuthAccessToken generatorAccessToken(String clientId, String code) {

        OAuthIssuerImpl oAuthIssuer = new OAuthIssuerImpl(generator);


        Optional<OAuthAuthorizeRequest> optional = authorizeRequestRepository.findByAuthorizationCode(code);

        OAuthAuthorizeRequest oAuthAuthorizeRequest = optional.orElseThrow(() -> new BusinessException("code不存在"));
        optional.filter(i -> i.getAuthState() == 2).orElseThrow(() -> new BusinessException("未同意授权"));
        optional.filter(i -> i.getStatus() == 0).orElseThrow(() -> new BusinessException("code 已使用"));


        oAuthAuthorizeRequest.setStatus(1);
        authorizeRequestRepository.saveAndFlush(oAuthAuthorizeRequest);

        Optional<OAuthUserAuthorize> oAuthUserAuthorize = oAuthUserAuthorizeRepository.findByClientIdAndUserId(clientId, optional.get().getUserId());

        OAuthAccessToken oAuthAccessToken = null;
        try {
            oAuthAccessToken = new OAuthAccessToken();
            oAuthAccessToken.setCreationDate(new Date());
            oAuthAccessToken.setModificationDate(new Date());
            oAuthAccessToken.setExpires(3600);
            oAuthAccessToken.setRefreshToken(oAuthIssuer.refreshToken());
            oAuthAccessToken.setResourceOwnerId(oAuthUserAuthorize.get().getOauthUserId());
            oAuthAccessToken.setToken(oAuthIssuer.accessToken());
            oAuthAccessToken.setClientId(clientId);
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }

        oAuthAccessTokenRepository.saveAndFlush(oAuthAccessToken);

        return oAuthAccessToken;
    }
}
