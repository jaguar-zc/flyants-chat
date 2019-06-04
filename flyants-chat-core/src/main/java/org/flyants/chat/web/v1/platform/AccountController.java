package org.flyants.chat.web.v1.platform;

import lombok.extern.slf4j.Slf4j;
import org.flyants.chat.configuration.PageResult;
import org.flyants.chat.domain.entity.platform.People;
import org.flyants.chat.domain.service.PeopleService;
import org.flyants.chat.utils.JWTManager;
import org.flyants.chat.utils.ResponseDataUtils;
import org.flyants.common.ResponseData;
import org.flyants.common.annotation.Anonymous;
import org.flyants.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/5/17 15:02
 * @Version v1.0
 */
@RestController
@RequestMapping(PlatformVersion.version + "/account")
@Slf4j
public class AccountController {


    @Autowired
    PeopleService peopleService;



    @GetMapping("/list")
    public PageResult<People> findList(@RequestParam(required = false,name = "page",defaultValue = "1") Integer page,
                                            @RequestParam(required = false,name = "size",defaultValue = "10") Integer size,
                                            @RequestParam(required = false,name = "searchBy") String searchBy,
                                            @RequestParam(required = false,name = "keyword") String keyWord) {
        return peopleService.findList(page, size, searchBy, keyWord);
    }







    @GetMapping("/{peopleId}")
    public ResponseData<Object> get(@PathVariable("peopleId") String id) {
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


    @Anonymous
    @PostMapping("/create")
    public ResponseData<Object> create(@RequestParam("phone") String phone,@RequestParam("nickName")String nickName) {
        String id = JWTManager.get();
        log.info("id:{} ", id);
        peopleService.createPeople(phone,nickName);
        return ResponseDataUtils.buildSuccess();
    }


    @GetMapping("/info")
    public ResponseData<Object> info() {
        String id = JWTManager.get();
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
