package org.flyants.authorize.service;

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
}
