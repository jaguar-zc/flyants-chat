package io.sufeng.app.restful.app;

import lombok.extern.slf4j.Slf4j;
import io.sufeng.context.configuration.PageResult;
import io.sufeng.context.domain.entity.message.Message;
import io.sufeng.context.domain.service.MessageService;
import io.sufeng.context.dto.app.MessageUserSimpleInfoDto;
import io.sufeng.context.dto.app.PublishMessageDto;
import io.sufeng.context.utils.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhangchao
 * @Date 2019/5/28 17:11
 * @Version v1.0
 */
@RestController
@RequestMapping(AppVersion.version+"/message")
@Slf4j
public class AppMessageController {


    @Autowired
    private MessageService messageService;


    @GetMapping("/getPeopleSimpleInfoByPeopleId")
    public MessageUserSimpleInfoDto getPeopleSimpleInfoByPeopleId(String peopleId){
        return messageService.getPeopleSimpleInfoByPeopleId(peopleId);
    }


    @GetMapping("/getMessageUserSimpleInfo")
    public MessageUserSimpleInfoDto getPeopleSimpleInfo(String messageUserId){
        return messageService.getPeopleSimpleInfo(messageUserId);
    }


    @PostMapping("/publish")
    public void publishMessage(@RequestBody PublishMessageDto publishMessageDto){
        String peopleId = JWTManager.get();
        messageService.publishMessage(peopleId,publishMessageDto);
    }


    @GetMapping("/{conversationId}/list")
    public PageResult<Message> list(@RequestParam(required = false,name = "page",defaultValue = "1") Integer page,
                                    @RequestParam(required = false,name = "size",defaultValue = "10") Integer size,
                                    @PathVariable("conversationId") String conversationId) {
        String peopleId = JWTManager.get();
        return messageService.list(page, size, conversationId);
    }

}
