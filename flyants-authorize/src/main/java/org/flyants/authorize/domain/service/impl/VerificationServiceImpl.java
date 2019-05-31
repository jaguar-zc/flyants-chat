package org.flyants.authorize.domain.service.impl;

import org.flyants.authorize.configuration.Constents;
import org.flyants.authorize.domain.service.VerificationService;
import org.flyants.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/5/31 17:10
 * @Version v1.0
 */
@Service
public class VerificationServiceImpl implements VerificationService {


    @Override
    public Boolean checkForResult(String phone, String smsCode) {
        List<String> stringList = Arrays.asList(Constents.DEFAUTL_SMSCODE);
        //获取验证码



        if(!stringList.contains(smsCode)){
            return false;
        }

        return true;

    }

    @Override
    public void check(String phone, String smsCode) {
        if(!checkForResult(phone,smsCode)){
            throw new BusinessException("验证码错误");
        }
    }
}
