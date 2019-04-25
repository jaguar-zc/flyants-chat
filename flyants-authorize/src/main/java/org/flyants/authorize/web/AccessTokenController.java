package org.flyants.authorize.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.flyants.authorize.domain.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangchao
 * @Date 2019/4/25 16:32
 * @Version v1.0
 */
@RestController
@RequestMapping("/oauth2/access_token")
@Slf4j
public class AccessTokenController {



    @Autowired
    private AuthorizeService authorizeService;

    @GetMapping
    public ResponseEntity<String> accessToken(HttpServletRequest request) throws OAuthSystemException, OAuthProblemException {
        //构建OAuth请求
        OAuthTokenRequest tokenRequest = new OAuthTokenRequest(request);
        //获取OAuth客户端Id
        String clientId = tokenRequest.getClientId();
        //校验客户端Id是否正确
        if(!authorizeService.checkClientId(clientId)){
            OAuthResponse oAuthResponse = OAuthASResponse
                    .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                    .setErrorDescription("无效的客户端Id")
                    .buildJSONMessage();
            System.out.println(oAuthResponse.getBody());
            return new ResponseEntity<String>(oAuthResponse.getBody(), HttpStatus.valueOf(oAuthResponse.getResponseStatus()));
        }

        //检查客户端安全KEY是否正确
        if(!authorizeService.checkClientSecret(tokenRequest.getClientSecret())){
            OAuthResponse response = OAuthResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                    .setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT)
                    .setErrorDescription("客户端安全KEY认证不通过")
                    .buildJSONMessage();
            return new ResponseEntity<String>(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
        }

        //检查redirect_uri是否和认证的一致
        if(!authorizeService.checkRedirectUri(tokenRequest.getRedirectURI())){
            OAuthResponse response = OAuthResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                    .setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT)
                    .setErrorDescription("客户端认证不通过")
                    .buildJSONMessage();
            return new ResponseEntity<String>(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
        }
        //验证类型，有AUTHORIZATION_CODE/PASSWORD/REFRESH_TOKEN/CLIENT_CREDENTIALS
        if(tokenRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())){
            String authCode = tokenRequest.getParam(OAuth.OAUTH_CODE);
            if(!authorizeService.checkAuthCode(authCode)){
                OAuthResponse response = OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                        .setError(OAuthError.TokenResponse.INVALID_GRANT)
                        .setErrorDescription("错误的授权码")
                        .buildJSONMessage();
                return new ResponseEntity<String>(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            }
            //生成访问令牌
            OAuthIssuerImpl authIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            String accessToken = authIssuerImpl.accessToken();
            //生成OAuth响应
            OAuthResponse response = OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(accessToken)
                    .setExpiresIn("1000")
                    .buildJSONMessage();
            System.out.println(response.getBody());
//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<String>(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
        }
        OAuthResponse response = OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                .setError(OAuthError.TokenResponse.UNSUPPORTED_GRANT_TYPE)
                .setErrorDescription("不支持此授权类型")
                .buildJSONMessage();
        return new ResponseEntity<String>(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
    }
}
