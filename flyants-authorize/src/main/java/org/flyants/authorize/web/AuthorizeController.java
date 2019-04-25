package org.flyants.authorize.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.flyants.authorize.service.AuthorizeService;
import org.flyants.authorize.utils.ResponseData;
import org.flyants.authorize.utils.ResponseDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

/**
 * 用户授权有四种模式：
 *
 * 授权码模式（authorization code）
 * 简化模式（implicit）
 * 密码模式（resource owner password credentials）
 * 客户端模式（client credentials）
 *
 *
 * @Author zhangchao
 * @Date 2019/4/25 11:48
 * @Version v1.0
 */
@RestController
@RequestMapping("/oauth2")
@Slf4j
public class AuthorizeController {

    @Autowired
    private AuthorizeService authorizeService;


    /**
     * 授权码模式（authorization code）
     * @param request
     * @return
     * @throws OAuthSystemException
     * @throws OAuthProblemException
     * @throws URISyntaxException
     */
    @GetMapping(value = "/authorize",params = {"response_type=code"})
    public Object authorize(String response_type, String client_id, String redirect_uri, String scope, String state)  {
       if (!authorizeService.checkClientId(client_id)) {
            return ResponseDataUtil.buildError("无效的 client_id");
        }
        //检查用户是否登陆和同意授权，如何还没登陆则跳转至登陆页面,然后进行授权提示
        //待开发。。。
        //生成授权码
        //ResponseType仅支持CODE和TOKEN
        String authCode = authorizeService.authorizationCode();
        System.out.println("授权码="+authCode);
        //获取客户端重定向地址

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:"+redirect_uri+"?code="+authCode);
        return mav;
    }


    /**
     * 简化模式（implicit）
     * @return
     */
    @GetMapping(value = "/authorize",params = {"response_type=implicit"})
    public Object authorizeimplicit(HttpServletRequest request)throws OAuthSystemException, OAuthProblemException {
        ModelAndView mav = new ModelAndView();
        // 构建OAuth请求
        OAuthAuthzRequest oAuthzRequest = new OAuthAuthzRequest(request);
        // 获取OAuth客户端Id
        String clientId = oAuthzRequest.getClientId();
        // 校验客户端Id是否正确
        if (!authorizeService.checkClientId(clientId)) {
            mav.addObject("msg", "无效的客户ID");
            mav.setViewName("forward:/ljdp/oauth/authorizefail");
            return mav;
        }
        //检查用户是否登陆和同意授权，如何还没登陆则跳转至登陆页面,然后进行授权提示
        //待开发。。。
        //生成授权码
        String authCode = null;
        String responseType = oAuthzRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
        //ResponseType仅支持CODE和TOKEN
        if(responseType.equals(ResponseType.CODE.toString())){
            OAuthIssuerImpl oAuthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            authCode = oAuthIssuerImpl.authorizationCode();
            System.out.println("授权码="+authCode);
        }
        //获取客户端重定向地址
        String redirectURI = oAuthzRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
        return "redirect:"+redirectURI+"?code="+authCode;
    }



}
