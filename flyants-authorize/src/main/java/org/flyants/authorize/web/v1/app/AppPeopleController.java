package org.flyants.authorize.web.v1.app;

import lombok.extern.slf4j.Slf4j;
import org.flyants.authorize.domain.service.PeopleService;
import org.flyants.authorize.dto.app.PeopleInfoDto;
import org.flyants.authorize.dto.app.PeopleSimpleDto;
import org.flyants.authorize.utils.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        String peopleId = JWTManager.get();
        return  peopleService.info(peopleId);
    }

    @PutMapping("/updatePeopleInfo")
    public void updatePeopleInfo(@RequestBody PeopleInfoDto peopleInfoDto){
        String peopleId = JWTManager.get();
        peopleService.updatePeopleInfo(peopleId,peopleInfoDto);
    }

    @PutMapping("/editPeopleIntroduction")
    public void editPeopleIntroduction(String introduction){
        String peopleId = JWTManager.get();
        peopleService.editPeopleIntroduction(peopleId,introduction);
    }

    @GetMapping("/assistPeople")
    public void assistPeople(String assistPeopleId){
        String peopleId = JWTManager.get();
        peopleService.assistPeople(peopleId,assistPeopleId);
    }

}
