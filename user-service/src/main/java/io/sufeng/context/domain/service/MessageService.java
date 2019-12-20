package io.sufeng.context.domain.service;

import io.sufeng.context.configuration.PageResult;
import io.sufeng.context.domain.entity.message.Message;
import io.sufeng.context.dto.app.MessageUserSimpleInfoDto;
import io.sufeng.context.dto.app.PublishMessageDto;

/**
 * @Author zhangchao
 * @Date 2019/5/24 16:22
 * @Version v1.0
 */
public interface MessageService {

    void publishMessage(String peopleId, PublishMessageDto dynamic);

    PageResult<Message> list(Integer page, Integer size, String conversationId);

    MessageUserSimpleInfoDto getPeopleSimpleInfo(String messageUserId);

    MessageUserSimpleInfoDto getPeopleSimpleInfoByPeopleId(String peopleId);
}
