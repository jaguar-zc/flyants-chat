package org.flyants.chat.domain.service;

import org.flyants.chat.configuration.PageResult;
import org.flyants.chat.domain.entity.platform.comments.CommentsType;
import org.flyants.chat.dto.app.CommentsPublishDto;
import org.flyants.chat.dto.app.CommentsSimpleDto;

/**
 * @Author zhangchao
 * @Date 2019/5/28 10:04
 * @Version v1.0
 */
public interface CommentsService {
    void publish(String peopleId, CommentsPublishDto publishDto);

    PageResult<CommentsSimpleDto> list(Integer page, Integer size, CommentsType commentsType, String resourceId);
}
