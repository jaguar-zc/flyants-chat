package org.flyants.authorize.dto.app;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/5/28 11:18
 * @Version v1.0
 */
@Getter
@Setter
public class EditConversationDto {


    private String name;
    private String icon;
    private List<String> tags;
    private Integer top;//置顶
    private Integer dontDisturb;//免打扰
}
