package org.flyants.authorize.web;

import org.flyants.authorize.user.AuthUserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangchao
 * @Date 2019/4/25 14:51
 * @Version v1.0
 */
@RestController
@RequestMapping("/user")
public class UserResourceController {

    @GetMapping("/resource")
    public AuthUserInfo resource(String access_token) {
        // ... validate access token
        if(!"test".equals(access_token)){
            throw new RuntimeException("access_token error!");
        }
        //验证通过返回用户信息

        return new AuthUserInfo("test", "测试");

    }
}
