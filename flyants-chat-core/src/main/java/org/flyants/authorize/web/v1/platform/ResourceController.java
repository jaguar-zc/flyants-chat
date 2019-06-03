package org.flyants.authorize.web.v1.platform;

import lombok.extern.slf4j.Slf4j;
import org.flyants.authorize.domain.entity.oauth2.OAuthClientResource;
import org.flyants.authorize.domain.service.ClientResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/5/20 13:46
 * @Version v1.0
 */
@RestController
@RequestMapping(PlatformVersion.version + "/resource")
@Slf4j
public class ResourceController {


    @Autowired
    private ClientResourceService clientResourceService;


    @GetMapping("/list")
    public List<OAuthClientResource> findAll(){
        return clientResourceService.findAll();
    }

}
