package org.flyants.authorize.web.oauth;

import lombok.extern.slf4j.Slf4j;
import org.flyants.authorize.domain.service.AuthorizeService;
import org.flyants.authorize.oauth2.OAuthAuthorizeRequest;
import org.flyants.authorize.utils.ResponseDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 简化模式（implicit）
 *
 *
 * @Author zhangchao
 * @Date 2019/4/25 11:48
 * @Version v1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1.0/oauth2")
public class AuthorizeImplicitController {

    @Autowired
    private AuthorizeService authorizeService;

    /**
     * 简化模式（implicit）
     * @return
     */
    @GetMapping(value = "/authorize",params = {"response_type=implicit"})
    public Object authorizeimplicit(String response_type, String client_id, String redirect_uri, String scope, String state) {
        if (!authorizeService.checkClientId(client_id)) {
            return ResponseDataUtils.buildError("无效的 client_id");
        }
        //检查用户是否登陆和同意授权，如何还没登陆则跳转至登陆页面,然后进行授权提示
        //待开发。。。
        //生成授权码
        //ResponseType仅支持CODE和TOKEN
        OAuthAuthorizeRequest authorize = authorizeService.authorization(response_type,client_id,redirect_uri,scope,state);
        String authCode = authorize.getAuthorizationCode();
        System.out.println("授权码="+authCode);
        //获取客户端重定向地址
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:"+redirect_uri+"?code="+authCode+"&state="+state);
        return mav;
    }

}
