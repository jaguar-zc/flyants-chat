package org.flyants.authorize.domain.service;

import org.flyants.authorize.oauth2.AuthorizeRequest;

/**
 * @Author zhangchao
 * @Date 2019/4/25 13:54
 * @Version v1.0
 */

public interface AuthorizeService {

    public boolean checkClientId(String clientId);

    public String authorizationCode();

    public boolean checkClientSecret(String clientSecret);

    public boolean checkRedirectUri(String redirectURI);

    public boolean checkAuthCode(String authCode);

    AuthorizeRequest authorization(String response_type, String client_id, String redirect_uri, String scope, String state);
}
