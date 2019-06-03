package org.flyants.authorize.domain.service;

import org.flyants.authorize.configuration.PageResult;
import org.flyants.authorize.domain.entity.platform.message.Message;
import org.flyants.authorize.dto.app.MessageUserSimpleInfoDto;
import org.flyants.authorize.dto.app.PublishMessageDto;

/**
 * @Author zhangchao
 * @Date 2019/5/24 16:22
 * @Version v1.0
 */
public interface MessageService {
    void publishMessage(String peopleId, PublishMessageDto dynamic);

    PageResult<Message> list(Integer page, Integer size, String conversationId);

    MessageUserSimpleInfoDto getPeopleSimpleInfo(String messageUserId);
}
