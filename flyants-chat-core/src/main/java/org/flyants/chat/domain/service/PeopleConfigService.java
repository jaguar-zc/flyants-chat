package org.flyants.chat.domain.service;

import org.flyants.chat.domain.entity.platform.PeopleConfig;
import org.flyants.chat.dto.app.PeopleConfigDto;

/**
 * @Author zhangchao
 * @Date 2019/6/6 12:40
 * @Version v1.0
 */
public interface PeopleConfigService {

    PeopleConfig findFirstByPeopleId(String peopleId);

    void updatePeopleConfig(String peopleId, PeopleConfigDto peopleConfig);
}
