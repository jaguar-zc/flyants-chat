package io.sufeng.app.restful.app;

import lombok.extern.slf4j.Slf4j;
import io.sufeng.context.domain.entity.message.Conversation;
import io.sufeng.context.domain.service.ConversationService;
import io.sufeng.context.dto.app.ConversationListDto;
import io.sufeng.context.dto.app.CreateConversationDto;
import io.sufeng.context.dto.app.CreateGroupConversationDto;
import io.sufeng.context.dto.app.EditConversationDto;
import io.sufeng.context.utils.JWTManager;
import io.sufeng.context.utils.ResponseDataUtils;
import io.sufeng.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * App会话
 * @Author zhangchao
 * @Date 2019/5/28 11:16
 * @Version v1.0
 */
@RestController
@RequestMapping(AppVersion.version+"/conversation")
@Slf4j
public class AppConversationController {


    @Autowired
    ConversationService conversationService;


    @PostMapping("/createSingleConversation")
    public ResponseData createSingleConversation(@Valid @RequestBody CreateConversationDto createConversationDto){
        String peopleId = JWTManager.get();
        String conversationId = conversationService.createSingleConversation(peopleId, createConversationDto.getFirendsMessageUserId());
        return ResponseDataUtils.buildSuccess(conversationId);
    }

    @PostMapping("/createGroupConversation")
    public ResponseData createGroupConversation(@Valid @RequestBody CreateGroupConversationDto createConversationDto){
        String peopleId = JWTManager.get();
        String conversationId = conversationService.createGroupConversation(peopleId,createConversationDto.getMessageUserIds());
        return ResponseDataUtils.buildSuccess(conversationId);
    }


    @PostMapping("/editConversation/{conversationId}")
    public void editConversation(@PathVariable("conversationId") String conversationId , @RequestBody EditConversationDto editConversationDto){
        String peopleId = JWTManager.get();
        conversationService.editConversation(peopleId,conversationId ,editConversationDto);
    }


    @PostMapping("/list")
    public List<ConversationListDto> list(){
        String peopleId = JWTManager.get();
        return conversationService.list(peopleId);
    }

    @GetMapping("/getConversation")
    public Conversation getConversation(@RequestParam("conversationId") String conversationId){
        return conversationService.getConversation(conversationId);
    }

}
