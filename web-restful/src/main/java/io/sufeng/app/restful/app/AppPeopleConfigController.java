package io.sufeng.app.restful.app;

import lombok.extern.slf4j.Slf4j;
import io.sufeng.context.domain.entity.PeopleConfig;
import io.sufeng.context.domain.service.PeopleConfigService;
import io.sufeng.context.dto.app.PeopleConfigDto;
import io.sufeng.context.utils.JWTManager;
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
