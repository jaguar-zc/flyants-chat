package org.flyants.authorize.dto.app;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Author zhangchao
 * @Date 2019/5/31 17:07
 * @Version v1.0
 */
public class SettingPasswordDto {
    @NotNull
    @Length(min = 6,max = 18)
    private String password;
    @NotNull
    @Length(min = 4,max = 4)
    private String smsCode;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
