package org.flyants.authorize.web.v1.platform;

import lombok.extern.slf4j.Slf4j;
import org.flyants.authorize.domain.service.PeopleService;
import org.flyants.authorize.domain.entity.platform.People;
import org.flyants.authorize.utils.ResponseDataUtils;
import org.flyants.authorize.web.v1.platform.dto.LoginReq;
import org.flyants.common.ResponseData;
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
@RequestMapping("/login")
@Slf4j
public class LoginController {


    @Autowired
    PeopleService peopleService;


    @PostMapping
    public ResponseData<Object> login(@RequestBody LoginReq loginReq) {
        log.info("username:{},password:{}", loginReq.getUsername(), loginReq.getPassword());
        Optional<People> people = peopleService.findByUsernameAndPassword(loginReq.getUsername(), loginReq.getPassword());
        if (people.isPresent()) {
            People people1 = people.get();
            log.info(people1.toString());
            return ResponseDataUtils.buildSuccess(people);
        } else {
            throw new BusinessException("用户名密码不存在");
        }
    }


}
