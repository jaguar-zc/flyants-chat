package io.sufeng.context.domain.service;

import io.sufeng.context.domain.entity.message.Conversation;
import io.sufeng.context.dto.app.ConversationListDto;
import io.sufeng.context.dto.app.EditConversationDto;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/5/28 11:21
 * @Version v1.0
 */
public interface ConversationService {

    String createSingleConversation(String peopleId, String firendsMessageUserId);

    String createGroupConversation(String peopleId, List<String> firendsMessageUserIds);

    String addMessageUserToGroupConversation(String conversationId,List<String> firendsMessageUserIds);

    List<ConversationListDto> list(String peopleId);

    void editConversation(String peopleId, String conversationId, EditConversationDto editConversationDto);

    Conversation getConversation(String conversationId);
}
