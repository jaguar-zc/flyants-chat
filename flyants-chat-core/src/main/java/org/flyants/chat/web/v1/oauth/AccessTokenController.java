package org.flyants.chat.web.v1.oauth;

import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.flyants.chat.domain.service.AuthorizeService;
import org.flyants.chat.domain.entity.oauth2.OAuthAccessToken;
import org.flyants.chat.utils.ResponseDataUtils;
import org.flyants.common.ResponseData;
import org.flyants.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhangchao
 * @Date 2019/4/25 16:32
 * @Version v1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1.0/oauth2/access_token")
public class AccessTokenController {


    @Autowired
    private AuthorizeService authorizeService;

    /**
     * 获取 access_token
     * @param grant_type authorization_code
     * @param code
     * @param client_id
     * @param client_secret
     * @return
     * @throws OAuthSystemException
     * @throws OAuthProblemException
     */
    @RequestMapping
    public ResponseData<String> accessToken(String grant_type,String code,String client_id,String client_secret) {

        //校验客户端Id是否正确
        if (!authorizeService.checkClientId(client_id)) {
            throw new BusinessException("无效的客户端Id");
        }

        //检查客户端安全KEY是否正确
        if (!authorizeService.checkClientSecret(client_id, client_secret)) {
            throw new BusinessException("客户端安全KEY认证不通过");
        }

        //验证类型，有AUTHORIZATION_CODE/PASSWORD/REFRESH_TOKEN/CLIENT_CREDENTIALS
        if (grant_type.equals(GrantType.AUTHORIZATION_CODE.toString())) {
            if (!authorizeService.checkAuthCode(code)) {
                throw new BusinessException("错误的授权码");
            }
            OAuthAccessToken oAuthAccessToken = authorizeService.generatorAccessToken(client_id, code);

            Map<String,Object> map = new HashMap<>();
            map.put("access_token",oAuthAccessToken.getToken());
            map.put("token_type","example");
            map.put("expires_in",oAuthAccessToken.getExpires());
            map.put("refresh_token",oAuthAccessToken.getRefreshToken());
            map.put("scope","");
            return ResponseDataUtils.buildSuccess(map);
        }
        throw new BusinessException("不支持的授权类型");
    }
}
