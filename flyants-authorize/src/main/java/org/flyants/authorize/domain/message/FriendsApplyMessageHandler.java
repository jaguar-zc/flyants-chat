package org.flyants.authorize.domain.message;

import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.common.utils.JSONUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 好友申请消息
 *
 * @Author zhangchao
 * @Date 2019/5/28 17:16
 * @Version v1.0
 */
@Slf4j
public class FriendsApplyMessageHandler implements MessageHandler {

    private static String APPLY_MESSAGE_USERID = "applyMessageUserId";
    private static String APPLY_NICKNAME = "applyNickName";
    private static String APPLY_ENCODEDPRINCIPAL = "applyEncodedPrincipal";
    private static String STATUS = "status";
    private static String REJECT = "reject";

    private String applyMessageUserId;
    private String applyNickName;
    private String applyEncodedPrincipal;
    private String status;//
    private String reject;

    @Override
    public MessageType getMessageType() {
        return MessageType.FRIENDS_APPLY;
    }

    @Override
    public String toBody() {
        Map<String, Object> map = new HashMap<>();
        map.put(APPLY_MESSAGE_USERID, applyMessageUserId);
        map.put(APPLY_NICKNAME, applyNickName);
        map.put(APPLY_ENCODEDPRINCIPAL, applyEncodedPrincipal);
        map.put(STATUS, status);
        map.put(REJECT, reject);
        return JSONUtils.buildJSON(map);
    }

    @Override
    public MessageHandler builder(String body) {
        log.info("{}", body);
        Map<String, Object> map = JSONUtils.parseJSON(body);
        this.applyMessageUserId = map.getOrDefault(APPLY_MESSAGE_USERID, "").toString();
        this.applyNickName = map.getOrDefault(APPLY_NICKNAME, "").toString();
        this.applyEncodedPrincipal = map.getOrDefault(APPLY_ENCODEDPRINCIPAL, "").toString();
        this.status = map.getOrDefault(STATUS, "").toString();
        this.reject = map.getOrDefault(REJECT, "").toString();
        return this;
    }

}
