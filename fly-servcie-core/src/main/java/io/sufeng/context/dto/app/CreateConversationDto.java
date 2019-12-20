package io.sufeng.context.dto.app;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @Author zhangchao
 * @Date 2019/5/28 11:18
 * @Version v1.0
 */
@Getter
@Setter
public class CreateConversationDto {

    @NotNull
    private String firendsMessageUserId;
}
