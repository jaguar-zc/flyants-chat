package org.flyants.authorize.domain.message;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Author zhangchao
 * @Date 2019/5/28 17:17
 * @Version v1.0
 */
public interface MessageHandler {

    @JsonIgnore
    MessageType getMessageType();

    @JsonIgnore
    String toBody();

    @JsonIgnore
    MessageHandler builder(String body);
}
