package org.flyants.chat.web.v1.app;

import lombok.extern.slf4j.Slf4j;
import org.flyants.chat.configuration.PageResult;
import org.flyants.chat.domain.entity.platform.comments.CommentsType;
import org.flyants.chat.domain.service.CommentsService;
import org.flyants.chat.dto.app.CommentsPublishDto;
import org.flyants.chat.dto.app.CommentsSimpleDto;
import org.flyants.chat.utils.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author zhangchao
 * @Date 2019/5/28 10:05
 * @Version v1.0
 */
@RestController
@RequestMapping(AppVersion.version+"/comments")
@Slf4j
public class AppCommentsController {

    @Autowired
    CommentsService commentsService;


    @PostMapping("/publish")
    public void publish(@Valid @RequestBody CommentsPublishDto publishDto){
        String peopleId = JWTManager.get();
        commentsService.publish(peopleId,publishDto);
    }


    @GetMapping("/list")
    public PageResult<CommentsSimpleDto> list(@RequestParam(required = false,name = "page",defaultValue = "1") Integer page,
                                                  @RequestParam(required = false,name = "size",defaultValue = "10") Integer size,
                                                  @RequestParam(name = "commentsType" ) CommentsType commentsType,
                                                  @RequestParam(name = "resourceId" ) String resourceId
                                              ) {
        return commentsService.list(page, size, commentsType,resourceId);
    }

}
