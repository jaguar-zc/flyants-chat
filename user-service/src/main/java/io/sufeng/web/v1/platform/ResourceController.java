package io.sufeng.web.v1.platform;

import io.sufeng.context.domain.service.ClientResourceService;
import lombok.extern.slf4j.Slf4j;
import io.sufeng.context.domain.entity.oauth2.OAuthClientResource;
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
