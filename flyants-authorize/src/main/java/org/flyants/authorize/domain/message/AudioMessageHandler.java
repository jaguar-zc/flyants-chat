package org.flyants.authorize.domain.message;

import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.common.utils.JSONUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhangchao
 * @Date 2019/5/28 17:16
 * @Version v1.0
 */
@Slf4j
public class AudioMessageHandler implements MessageHandler {

    private static String  FIELD_NAME = "url";

    private String value;

    @Override
    public MessageType getMessageType() {
        return MessageType.AUDIO;
    }

    @Override
    public String toBody() {
        Map<String,Object> map = new HashMap<>();
        map.put(FIELD_NAME,value);
        return JSONUtils.buildJSON(map);
    }

    @Override
    public MessageHandler builder(String body) {
        log.info("{}",body);
        Map<String, Object> map = JSONUtils.parseJSON(body);
        this.value = map.getOrDefault(FIELD_NAME,"").toString();
        return this;
    }

}
