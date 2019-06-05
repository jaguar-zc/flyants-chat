package org.flyants.chat.web.v1.platform.dto;

import org.flyants.chat.domain.entity.platform.LoginMethod;

import javax.validation.constraints.NotNull;

/**
 * @Author zhangchao
 * @Date 2019/5/17 13:45
 * @Version v1.0
 */
public class LoginReq {

    @NotNull
    private LoginMethod.LoginType method;
    @NotNull
    private String phone;
    @NotNull
    private String mark;

    public LoginMethod.LoginType getMethod() {
        return method;
    }

    public void setMethod(LoginMethod.LoginType method) {
        this.method = method;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
