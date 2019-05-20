package org.flyants.authorize.web.v1.platform;

import lombok.extern.slf4j.Slf4j;
import org.flyants.authorize.configuration.PageResult;
import org.flyants.authorize.domain.entity.oauth2.OAuthClient;
import org.flyants.authorize.domain.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public PageResult<OAuthClient> findList(@RequestParam(required = false,name = "page",defaultValue = "1") Integer page,
                                            @RequestParam(required = false,name = "page",defaultValue = "10") Integer size,
                                            @RequestParam(required = false,name = "searchBy") String searchBy,
                                            @RequestParam(required = false,name = "keyword") String keyWord) {
        return appService.findList(page, size, searchBy, keyWord);
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
