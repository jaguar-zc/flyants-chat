package org.flyants.authorize.web.v1.platform;

import lombok.extern.slf4j.Slf4j;
import org.flyants.authorize.domain.entity.platform.People;
import org.flyants.authorize.domain.service.PeopleService;
import org.flyants.authorize.utils.ResponseDataUtils;
import org.flyants.common.ResponseData;
import org.flyants.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/5/17 15:02
 * @Version v1.0
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {



    @Autowired
    PeopleService peopleService;


    @GetMapping("/{peopleId}")
    public ResponseData<Object> info(@PathVariable("peopleId") Long id) {
        log.info("id:{} ", id);
        Optional<People> people = peopleService.findPeopleById(id);
        if (people.isPresent()) {
            People people1 = people.get();
            log.info(people1.toString());
            return ResponseDataUtils.buildSuccess(people.get());
        } else {
            throw new BusinessException("用户不存在");
        }
    }



}
