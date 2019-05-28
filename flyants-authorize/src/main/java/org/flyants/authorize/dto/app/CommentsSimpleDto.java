package org.flyants.authorize.dto.app;

import lombok.Getter;
import lombok.Setter;
import org.flyants.authorize.domain.entity.platform.comments.CommentsType;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author zhangchao
 * @Date 2019/5/28 10:07
 * @Version v1.0
 */
@Getter
@Setter
public class CommentsSimpleDto {

    private String id;
    private String resourceId;
    private Date createTime;
    private String text;
    private String nickName;
    private String encodedPrincipal;
    private Integer level;//深度 1， 2
    private String replyNickName;//回复${XX}:${text}
}
