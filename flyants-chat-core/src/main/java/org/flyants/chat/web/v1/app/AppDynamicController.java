package org.flyants.chat.web.v1.app;

import lombok.extern.slf4j.Slf4j;
import org.flyants.chat.configuration.PageResult;
import org.flyants.chat.domain.service.DynamicService;
import org.flyants.chat.dto.app.DynamicAddDto;
import org.flyants.chat.dto.app.DynamicDto;
import org.flyants.chat.utils.JWTManager;
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
