package io.sufeng.imimpl.netty.message;

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
public class NettyMessage {

    private String id;

    private String toId;

    private String sendId;

    private String type;

    private String body;

    private long sendTime;

    private Integer view;

}
