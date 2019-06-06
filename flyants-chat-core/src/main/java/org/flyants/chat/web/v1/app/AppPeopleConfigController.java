package org.flyants.chat.web.v1.app;

import lombok.extern.slf4j.Slf4j;
import org.flyants.chat.domain.entity.platform.PeopleConfig;
import org.flyants.chat.domain.service.PeopleConfigService;
import org.flyants.chat.dto.app.PeopleConfigDto;
import org.flyants.chat.utils.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhangchao
 * @Date 2019/6/6 11:40
 * @Version v1.0
 */
@RestController
@RequestMapping(AppVersion.version + "/people/config")
@Slf4j
public class AppPeopleConfigController {

    @Autowired
    PeopleConfigService peopleConfigService;

    @GetMapping
    public PeopleConfig getPeopleConfig() {
        String peopleId = JWTManager.get();
        return peopleConfigService.findFirstByPeopleId(peopleId);
    }

    @PutMapping
    public void updatePeopleConfig(@RequestBody PeopleConfigDto peopleConfig) {
        String peopleId = JWTManager.get();
        peopleConfigService.updatePeopleConfig(peopleId, peopleConfig);
    }

}
