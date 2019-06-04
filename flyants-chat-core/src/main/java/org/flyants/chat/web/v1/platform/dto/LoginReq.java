package org.flyants.chat.web.v1.platform.dto;

import javax.validation.constraints.NotNull;

/**
 * @Author zhangchao
 * @Date 2019/5/17 13:45
 * @Version v1.0
 */
public class LoginReq {

    @NotNull
    private String username;
    @NotNull
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
