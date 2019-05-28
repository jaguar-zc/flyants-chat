package org.flyants.authorize.domain.service.impl;
import org.flyants.authorize.domain.entity.platform.message.Conversation.ConversationType;

import org.flyants.authorize.domain.entity.platform.message.Conversation;
import org.flyants.authorize.domain.entity.platform.message.ConversationUser;
import org.flyants.authorize.domain.entity.platform.message.MessageUser;
import org.flyants.authorize.domain.repository.ConversationRepository;
import org.flyants.authorize.domain.repository.MessageUserRepository;
import org.flyants.authorize.domain.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhangchao
 * @Date 2019/5/28 11:21
 * @Version v1.0
 */
@Service
public class ConversationServiceImpl implements ConversationService {


    @Autowired
    ConversationRepository conversationRepository;

    @Autowired
    MessageUserRepository messageUserRepository;


    @Override
    public void createConversation(String peopleId, String firendsMessageUserId) {
        MessageUser slefMessageUser = messageUserRepository.findByPeopleId(peopleId);
        String slefMessageUserId = slefMessageUser.getId();

        Conversation conversation = new Conversation();
        conversation.setType(ConversationType.SINGLE);
        conversation.setName("");
        conversation.setIcon("");
        conversation.setTags("");
        conversation.setTop(0);
        conversation.setDontDisturb(0);
        conversation.setMessageUserId(slefMessageUserId);

        conversationRepository.save(conversation);


        ConversationUser conversationUserSlef = new ConversationUser();
        conversationUserSlef.setMessageUserId(slefMessageUserId);
        conversationUserSlef.setConversationId(conversation.getId());



        ConversationUser conversationUserFirends = new ConversationUser();
        conversationUserFirends.setMessageUserId(firendsMessageUserId);
        conversationUserFirends.setConversationId(conversation.getId());



    }
}
