package io.sufeng.context.dto.app;

import lombok.Getter;
import lombok.Setter;
import io.sufeng.context.domain.message.MessageType;

/**
 * @Author zhangchao
 * @Date 2019/5/28 17:13
 * @Version v1.0
 */
@Getter
@Setter
public class PublishMessageDto {
    private String conversationId;
    private String body;
    private MessageType messageType;
}
