package org.flyants.authorize.web.v1.platform;

import lombok.extern.slf4j.Slf4j;
import org.flyants.authorize.configuration.PageResult;
import org.flyants.authorize.domain.entity.oauth2.OAuthClient;
import org.flyants.authorize.domain.service.AppService;
import org.flyants.authorize.utils.ResponseDataUtils;
import org.flyants.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangchao
 * @Date 2019/5/16 16:01
 * @Version v1.0
 */
@RestController
@RequestMapping("/app")
@Slf4j
public class AppController {

    @Autowired
    private AppService appService;


    @GetMapping("/list")
    public PageResult<OAuthClient> findList(Integer page,Integer size){
        return appService.findList(page,size);
    }


    @PostMapping
    public void add(OAuthClient oAuthClient){
        appService.save(oAuthClient);
    }

}
