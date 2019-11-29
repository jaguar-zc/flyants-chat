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

    String getUserId(String token);

    void online(String token);

    void offline(String token);

}
