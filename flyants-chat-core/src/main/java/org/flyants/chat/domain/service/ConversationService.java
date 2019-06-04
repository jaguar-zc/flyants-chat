package org.flyants.chat.domain.service;

import org.flyants.chat.dto.app.ConversationListDto;
import org.flyants.chat.dto.app.EditConversationDto;

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
}
