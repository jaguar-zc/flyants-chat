package io.sufeng.web.v1.platform;

import io.sufeng.context.configuration.PageResult;
import io.sufeng.context.domain.service.AppService;
import lombok.extern.slf4j.Slf4j;
import io.sufeng.context.domain.entity.oauth2.OAuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhangchao
 * @Date 2019/5/16 16:01
 * @Version v1.0
 */
@RestController
@RequestMapping(PlatformVersion.version + "/app")
@Slf4j
public class AppController {

    @Autowired
    private AppService appService;

    @GetMapping("/list")
    public PageResult<OAuthClient> findList(@RequestParam(required = false,name = "page",defaultValue = "1") Integer page,
                                            @RequestParam(required = false,name = "size",defaultValue = "10") Integer size,
                                            @RequestParam(required = false,name = "searchBy") String searchBy,
                                            @RequestParam(required = false,name = "keyword") String keyWord) {
        return appService.findList(page, size, searchBy, keyWord);
    }


    @GetMapping("/{id}")
    public OAuthClient find(@PathVariable("id") String clientId){
        return appService.find(clientId);
    }

    @PostMapping
    public void add(@RequestBody OAuthClient oAuthClient) {
        appService.save(oAuthClient);
    }


    @PutMapping("/{id}")
    public void update(@PathVariable("id") String id ,@RequestBody OAuthClient oAuthClient) {
        appService.update(id,oAuthClient);
    }

}
