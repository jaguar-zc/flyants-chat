package io.sufeng.impl.websocket.message;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 已充值终端的通讯基础消息
 *
 * @Author zhangchao
 * @Date 2019/3/21 13:11
 * @Version 1.0
 */
@Getter
@Setter
public class Msg {

    private String id;

    private String conversationId;

    private String messageUserId;

    private String messageType;

    private String body;

    private long sendTime;

    private Integer view;

}
