package org.flyants.chat.web.v1.app;

import lombok.extern.slf4j.Slf4j;
import org.flyants.chat.domain.entity.platform.message.Conversation;
import org.flyants.chat.domain.service.ConversationService;
import org.flyants.chat.dto.app.ConversationListDto;
import org.flyants.chat.dto.app.CreateConversationDto;
import org.flyants.chat.dto.app.CreateGroupConversationDto;
import org.flyants.chat.dto.app.EditConversationDto;
import org.flyants.chat.utils.JWTManager;
import org.flyants.chat.utils.ResponseDataUtils;
import org.flyants.common.ResponseData;
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
