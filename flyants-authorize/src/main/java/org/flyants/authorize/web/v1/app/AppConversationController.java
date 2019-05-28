package org.flyants.authorize.web.v1.app;

import lombok.extern.slf4j.Slf4j;
import org.flyants.authorize.domain.service.ConversationService;
import org.flyants.authorize.dto.app.CreateConversationDto;
import org.flyants.authorize.utils.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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




    @PostMapping("/publish")
    public void createConversation(@Valid @RequestBody CreateConversationDto createConversationDto){
        String peopleId = JWTManager.get();
        conversationService.createConversation(peopleId,createConversationDto.getFirendsMessageUserId());
    }




}
