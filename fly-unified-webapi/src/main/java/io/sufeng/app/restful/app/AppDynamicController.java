package io.sufeng.app.restful.app;

import lombok.extern.slf4j.Slf4j;
import io.sufeng.context.configuration.PageResult;
import io.sufeng.context.domain.service.DynamicService;
import io.sufeng.context.dto.app.DynamicAddDto;
import io.sufeng.context.dto.app.DynamicDto;
import io.sufeng.context.utils.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jagua on 2019/5/27.
 */
@RestController
@RequestMapping(AppVersion.version+"/dynamic")
@Slf4j
public class AppDynamicController {


    @Autowired
    private DynamicService dynamicService;


    @PostMapping("/publish")
    public void publishDynamic(@RequestBody DynamicAddDto dynamic){
        String peopleId = JWTManager.get();
        dynamicService.publishDynamic(peopleId,dynamic);
    }


    @GetMapping("/list/self")
    public PageResult<DynamicDto> listSelf(@RequestParam(required = false,name = "page",defaultValue = "1") Integer page,
                                       @RequestParam(required = false,name = "size",defaultValue = "10") Integer size) {
        String peopleId = JWTManager.get();
        return dynamicService.listSelf(page, size, peopleId);
    }


    @GetMapping("/list/friend")
    public PageResult<DynamicDto> listFriend(@RequestParam(required = false,name = "page",defaultValue = "1") Integer page,
                                       @RequestParam(required = false,name = "size",defaultValue = "10") Integer size) {
        String peopleId = JWTManager.get();
        return dynamicService.listFriend(page, size, peopleId);
    }

}
