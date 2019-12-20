package io.sufeng.context.domain.message;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhangchao
 * @Date 2019/5/28 17:16
 * @Version v1.0
 */
@Slf4j
@Messager(MessageType.TEXT)
public class TextMessage  {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
