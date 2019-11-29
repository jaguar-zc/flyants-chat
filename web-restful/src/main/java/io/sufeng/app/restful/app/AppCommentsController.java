package io.sufeng.app.restful.app;

import lombok.extern.slf4j.Slf4j;
import io.sufeng.context.configuration.PageResult;
import io.sufeng.context.domain.entity.comments.CommentsType;
import io.sufeng.context.domain.service.CommentsService;
import io.sufeng.context.dto.app.CommentsPublishDto;
import io.sufeng.context.dto.app.CommentsSimpleDto;
import io.sufeng.context.utils.JWTManager;
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
