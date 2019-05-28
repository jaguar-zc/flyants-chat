package org.flyants.authorize.domain.message;

/**
 * @Author zhangchao
 * @Date 2019/5/28 17:18
 * @Version v1.0
 */
public enum  MessageType {
    FRIENDS_APPLY(FriendsApplyMessageHandler.class),
    TEXT( TextMessageHandler.class),
    IMAGE( ImageMessageHandler.class),
    AUDIO( AudioMessageHandler.class);
//    VIDEO;

    private Class<? extends MessageHandler> messageHandler;

    MessageType(Class<? extends MessageHandler> messageHandler) {
        this.messageHandler = messageHandler;
    }

    public Class<? extends MessageHandler> getMessageHandler() {
        return messageHandler;
    }
}
