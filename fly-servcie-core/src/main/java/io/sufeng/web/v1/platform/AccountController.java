package io.sufeng.web.v1.platform;

import io.sufeng.context.configuration.PageResult;
import io.sufeng.context.domain.service.PeopleService;
import io.sufeng.context.utils.JWTManager;
import lombok.extern.slf4j.Slf4j;
import io.sufeng.context.domain.entity.People;
import io.sufeng.context.utils.ResponseDataUtils;
import io.sufeng.common.ResponseData;
import io.sufeng.common.annotation.Anonymous;
import io.sufeng.common.exception.BusinessException;
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
