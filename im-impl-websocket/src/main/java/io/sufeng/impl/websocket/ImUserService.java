package io.sufeng.impl.websocket;

/**
 * @description:
 * @author: sufeng
 * @create: 2019-11-29 15:28
 */
public interface ImUserService {

    default Boolean checked(String token) {
        return false;
    }

    default String getMessageUserId(String token){return "DEFAULT";}

    default void online(String host,String token){};

    default void offline(String host,String token){};

}
