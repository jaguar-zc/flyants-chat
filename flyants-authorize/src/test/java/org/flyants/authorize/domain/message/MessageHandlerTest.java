package org.flyants.authorize.domain.message;
import java.util.Date;
import org.flyants.authorize.domain.message.MessageType;

import org.flyants.authorize.domain.entity.platform.message.Message;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jagua on 2019/5/28.
 */
public class MessageHandlerTest {
    @Test
    public void toBody() throws Exception {
        Message message = new Message();
        message.setId("1");
        message.setConversationId("1");
        message.setMessageUserId("1");
        message.setMessageType(MessageType.TEXT);
        message.setBody("{\"text\":\"你好哇?\"}");
        message.setSendTime(new Date());
        message.setView(0);

    }

}