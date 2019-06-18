package org.flyants.chat.web.v1.app;

import lombok.extern.slf4j.Slf4j;
import org.flyants.chat.configuration.PageResult;
import org.flyants.chat.domain.entity.platform.People;
import org.flyants.chat.domain.service.PeopleService;
import org.flyants.chat.dto.app.DynamicDto;
import org.flyants.chat.dto.app.PeopleInfoDto;
import org.flyants.chat.dto.app.SettingPasswordDto;
import org.flyants.chat.dto.app.SimplePeopleInfoDto;
import org.flyants.chat.utils.JWTManager;
import org.flyants.chat.utils.ResponseDataUtils;
import org.flyants.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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

    @PutMapping("/setPassword")
    public void setPassword(@RequestBody @Valid SettingPasswordDto settingPassword){
        String peopleId = JWTManager.get();
        peopleService.setPassword(peopleId,settingPassword);
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

    @GetMapping("/{peopleId}/info")
    public ResponseData<People> getPeople(@PathVariable("peopleId") String peopleId){
        Optional<People> people = peopleService.findPeopleById(peopleId);
        if(people.isPresent()){
            return ResponseDataUtils.buildSuccess(people.get());
        }
        return ResponseDataUtils.buildError("用户不存在");
    }

    @GetMapping("/list/search")
    public PageResult<SimplePeopleInfoDto> listSearch(@RequestParam(required = false,name = "page",defaultValue = "1") Integer page,
                                                    @RequestParam(required = false,name = "size",defaultValue = "10") Integer size,
                                                    @RequestParam(required = false,name = "word") String word) {
        String peopleId = JWTManager.get();
        return peopleService.listSearch(page, size, peopleId,word);
    }

}
