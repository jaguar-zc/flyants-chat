package io.sufeng.context.domain.service;

import io.sufeng.context.domain.entity.PeopleConfig;
import io.sufeng.context.dto.app.PeopleConfigDto;

/**
 * @Author zhangchao
 * @Date 2019/6/6 12:40
 * @Version v1.0
 */
public interface PeopleConfigService {

    PeopleConfig findFirstByPeopleId(String peopleId);

    void updatePeopleConfig(String peopleId, PeopleConfigDto peopleConfig);
}
