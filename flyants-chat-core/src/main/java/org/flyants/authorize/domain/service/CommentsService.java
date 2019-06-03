package org.flyants.authorize.domain.service;

import org.flyants.authorize.configuration.PageResult;
import org.flyants.authorize.domain.entity.platform.comments.CommentsType;
import org.flyants.authorize.dto.app.CommentsPublishDto;
import org.flyants.authorize.dto.app.CommentsSimpleDto;

/**
 * @Author zhangchao
 * @Date 2019/5/28 10:04
 * @Version v1.0
 */
public interface CommentsService {
    void publish(String peopleId, CommentsPublishDto publishDto);

    PageResult<CommentsSimpleDto> list(Integer page, Integer size, CommentsType commentsType, String resourceId);
}
