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
public class ImageMessageHandler implements MessageHandler {

    private String small;
    private String source;

    @Override
    public MessageType getMessageType() {
        return MessageType.IMAGE;
    }

    @Override
    public String toBody() {
        Map<String,Object> map = new HashMap<>();
        map.put("small",small);
        map.put("source",source);
        return JSONUtils.buildJSON(map);
    }

    @Override
    public MessageHandler builder(String body) {
        log.info("{}",body);
        Map<String, Object> map = JSONUtils.parseJSON(body);
        this.small = map.getOrDefault("small","").toString();
        this.source = map.getOrDefault("source","").toString();
        return this;
    }

}
