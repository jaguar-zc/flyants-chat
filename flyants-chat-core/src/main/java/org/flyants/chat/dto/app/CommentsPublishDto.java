package org.flyants.chat.dto.app;

import lombok.Getter;
import lombok.Setter;
import org.flyants.chat.domain.entity.platform.comments.CommentsType;

import javax.validation.constraints.NotNull;

/**
 * @Author zhangchao
 * @Date 2019/5/28 10:07
 * @Version v1.0
 */
@Getter
@Setter
public class CommentsPublishDto {

    @NotNull
    private String resourceId;

    private String commentsId;
    @NotNull
    private CommentsType commentsType;
    @NotNull
    private String text;
}
