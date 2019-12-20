package org.flyants.oauth2.clientdemo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @Author zhangchao
 * @Date 2019/4/28 14:50
 * @Version v1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    RestTemplate restTemplate;


    public String index(){


        return "index";
    }


    public String flyantsCallback(String code){

        return "" ;
    }


    @GetMapping("/flyants_login")
    public String authLogin(){
        return "";
    }

    public String getAccessToken(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://88.88.10.82:8000/api/v1.0/oauth2/access_token", String.class);
        String body = responseEntity.getBody();

        return body;
    }

}
