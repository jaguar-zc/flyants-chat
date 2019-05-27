package org.flyants.authorize.web.v1.app;

import lombok.extern.slf4j.Slf4j;
import org.flyants.authorize.domain.service.PeopleService;
import org.flyants.authorize.dto.app.PeopleInfoDto;
import org.flyants.authorize.utils.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangchao
 * @Date 2019/5/27 17:11
 * @Version v1.0
 */
@RestController
@RequestMapping(AppVersion.version+"/people")
@Slf4j
public class AppPeopleController {


    @Autowired
    PeopleService peopleService;

    @GetMapping("/info")
    public PeopleInfoDto info(){
        Long peopleId = JWTManager.get();
        return  peopleService.info(peopleId);
    }

    @PostMapping("/editPeopleIntroduction")
    public void editPeopleIntroduction(String introduction){
        Long peopleId = JWTManager.get();
        peopleService.editPeopleIntroduction(peopleId,introduction);
    }

}
