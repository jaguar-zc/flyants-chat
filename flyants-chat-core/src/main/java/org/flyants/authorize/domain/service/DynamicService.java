package org.flyants.authorize.domain.service;

import org.flyants.authorize.configuration.PageResult;
import org.flyants.authorize.dto.app.DynamicAddDto;
import org.flyants.authorize.dto.app.DynamicDto;

import java.util.List;

/**
 * Created by jagua on 2019/5/27.
 */
public interface DynamicService {

    void publishDynamic(String peopleId, DynamicAddDto dynamic);

    PageResult<DynamicDto> list(Integer page, Integer size, List<String> peopleId);

    PageResult<DynamicDto> listSelf(Integer page, Integer size, String peopleId);

    PageResult<DynamicDto> listFriend(Integer page, Integer size, String peopleId);
}
