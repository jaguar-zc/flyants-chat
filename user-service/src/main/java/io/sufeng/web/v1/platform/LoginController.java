package io.sufeng.web.v1.platform;

import io.sufeng.context.domain.service.PeopleService;
import io.sufeng.web.v1.platform.dto.LoginReq;
import lombok.extern.slf4j.Slf4j;
import io.sufeng.context.utils.ResponseDataUtils;
import io.sufeng.common.ResponseData;
import io.sufeng.common.annotation.Anonymous;
import io.sufeng.common.exception.BusinessException;
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
        Optional<String> people = peopleService.login(loginReq);
        if (people.isPresent()) {
            log.info(people.get());
            return ResponseDataUtils.buildSuccess().addAttrs("token",people.get());
        } else {
            throw new BusinessException("用户名密码不存在");
        }
    }


}
