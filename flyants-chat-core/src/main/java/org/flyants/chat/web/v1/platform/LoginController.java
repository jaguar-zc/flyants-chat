package org.flyants.chat.web.v1.platform;

import lombok.extern.slf4j.Slf4j;
import org.flyants.chat.domain.service.PeopleService;
import org.flyants.chat.utils.ResponseDataUtils;
import org.flyants.chat.web.v1.platform.dto.LoginReq;
import org.flyants.common.ResponseData;
import org.flyants.common.annotation.Anonymous;
import org.flyants.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/4/26 13:46
 * @Version v1.0
 */
@RestController
@RequestMapping(PlatformVersion.version + "/login")
@Slf4j
public class LoginController {


    @Autowired
    PeopleService peopleService;



    @Anonymous
    @PostMapping
    public ResponseData<Object> login(@RequestBody LoginReq loginReq) {
        log.info("username:{},password:{}", loginReq.getPhone(), loginReq.getMark());
        Optional<String> people = peopleService.loginByPassword(loginReq.getPhone(), loginReq.getMark());
        if (people.isPresent()) {
            log.info(people.get());
            return ResponseDataUtils.buildSuccess().addAttrs("token",people.get());
        } else {
            throw new BusinessException("用户名密码不存在");
        }
    }


}
