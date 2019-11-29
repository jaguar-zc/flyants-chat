package io.sufeng.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 模拟客户端服务器后台  用来接收认证服务器的授权码    客户端服务器拿到授权码向授权服务器换取access_token
 * @Author zhangchao
 * @Date 2019/4/25 14:35
 * @Version v1.0
 */
@RestController
@RequestMapping("/clientserver")
@Slf4j
public class ClientServerController {

    @GetMapping("/callback")
    public void callback(String code, String state){
        log.info("code:{},state:{}",code,state);
    }
}
