package org.flyants.authorize.domain.message;

import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.common.utils.JSONUtils;
import org.flyants.common.utils.JsonUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhangchao
 * @Date 2019/5/28 17:16
 * @Version v1.0
 */
@Slf4j
public class TextMessageHandler implements MessageHandler {

    private static String TEXT ="text";

    private String text;

    @Override
    public MessageType getMessageType() {
        return MessageType.TEXT;
    }

    @Override
    public String toBody() {
        Map<String, Object> objectObjectMap = new HashMap<>();
        objectObjectMap.put(TEXT,text);
        return JsonUtils.obj2json(objectObjectMap);
    }

    @Override
    public MessageHandler builder(String body) {
        log.info("{}",body);
        TextMessageHandler textMessageHandler = JsonUtils.json2pojo(body, TextMessageHandler.class);
        this.text = textMessageHandler.getText();
        return textMessageHandler;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
