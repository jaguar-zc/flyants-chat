package io.sufeng.imimpl.netty.message;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @description:
 * @author: sufeng
 * @create: 2019-11-22 17:14
 */
@Builder
@Setter
@Getter
public class ErrorMsg extends NettyMessage {
    public ErrorMsg() {
        this.setType("ERROR_MSG");
        this.setId(UUID.randomUUID().toString());
        this.setSendTime(System.currentTimeMillis());
        this.setBody("");
        this.setSendId("");
        this.setToId("");
        this.setView(0);
    }

    public ErrorMsg(String body) {
        this();
        this.setBody(body);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
