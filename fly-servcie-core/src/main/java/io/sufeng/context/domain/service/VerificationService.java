package io.sufeng.context.domain.service;

/**
 * @Author zhangchao
 * @Date 2019/5/31 17:10
 * @Version v1.0
 */
public interface VerificationService {
    void check(String phone, String smsCode);
    Boolean checkForResult(String phone, String smsCode);
}
