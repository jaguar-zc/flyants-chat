package org.flyants.authorize.domain.service;

import org.flyants.authorize.domain.entity.oauth2.OAuthAccessToken;
import org.flyants.authorize.domain.entity.oauth2.OAuthAuthorizeRequest;
import org.flyants.authorize.domain.entity.oauth2.OAuthClient;
import org.flyants.authorize.domain.entity.oauth2.OAuthUserAuthorize;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/4/25 13:54
 * @Version v1.0
 */

public interface AuthorizeService {

    boolean checkClientId(String clientId);

    OAuthClient findOAuthClinetByClientId(String clientId);

    boolean checkClientSecret(String clientId,String clientSecret);

    boolean checkRedirectUri(String clientId,String redirectURI);

    boolean checkAuthCode(String authCode);

    OAuthAuthorizeRequest authorization(String response_type, String client_id, String redirect_uri, String scope, String state);

    boolean checkAccessToken(String accessToken);

    Optional<String> findPeopleIdByOpenId(String accessToken, String openId);

    OAuthUserAuthorize findOauthUserAuthorizeByClientIdAndUserId(String clientId, String userId);

    OAuthAccessToken generatorAccessToken(String clientId, String code);
}
