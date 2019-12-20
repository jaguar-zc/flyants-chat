package io.sufeng.context.domain.service;

import io.sufeng.context.configuration.PageResult;
import io.sufeng.context.domain.entity.comments.CommentsType;
import io.sufeng.context.dto.app.CommentsPublishDto;
import io.sufeng.context.dto.app.CommentsSimpleDto;

/**
 * @Author zhangchao
 * @Date 2019/5/28 10:04
 * @Version v1.0
 */
public interface CommentsService {
    void publish(String peopleId, CommentsPublishDto publishDto);

    PageResult<CommentsSimpleDto> list(Integer page, Integer size, CommentsType commentsType, String resourceId);
}
