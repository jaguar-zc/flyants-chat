package org.flyants.authorize.web.v1.app;

import lombok.extern.slf4j.Slf4j;
import org.flyants.authorize.domain.service.PeopleService;
import org.flyants.authorize.utils.JWTManager;
import org.flyants.authorize.utils.ResponseDataUtils;
import org.flyants.authorize.web.v1.platform.PlatformVersion;
import org.flyants.authorize.web.v1.platform.dto.LoginReq;
import org.flyants.common.ResponseData;
import org.flyants.common.annotation.Anonymous;
import org.flyants.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/4/26 13:46
 * @Version v1.0
 */
@RestController
@RequestMapping(AppVersion.version)
@Slf4j
public class AppLoginController {


    @Autowired
    PeopleService peopleService;

    @Anonymous
    @PostMapping("/login")
    public ResponseData<Object> login(@Valid @RequestBody LoginReq loginReq) {
        log.info("login => username:{},password:{}", loginReq.getUsername(), loginReq.getPassword());
        Optional<String> token = peopleService.loginByPassword(loginReq.getUsername(), loginReq.getPassword());
        if (token.isPresent()) {
            log.info(token.get());
            return ResponseDataUtils.buildSuccess().addAttrs("token",token.get());
        } else {
            throw new BusinessException("用户名密码不存在");
        }
    }

    @PostMapping("/logout")
    public ResponseData<Object> logout() {
        Long aLong = JWTManager.get();
        log.info("logout:{}", aLong);
        peopleService.logout(aLong);
        return ResponseDataUtils.buildSuccess();
    }

}
