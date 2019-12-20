package io.sufeng.context.domain.service;

import io.sufeng.context.configuration.PageResult;
import io.sufeng.context.dto.app.DynamicAddDto;
import io.sufeng.context.dto.app.DynamicDto;

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
