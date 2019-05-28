package org.flyants.authorize.domain.message;

/**
 * @Author zhangchao
 * @Date 2019/5/28 17:17
 * @Version v1.0
 */
public interface MessageHandler {

    MessageType getMessageType();

    String toBody();

    MessageHandler builder(String body);
}
