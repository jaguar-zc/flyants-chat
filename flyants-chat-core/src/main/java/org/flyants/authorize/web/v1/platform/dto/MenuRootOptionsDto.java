package org.flyants.authorize.web.v1.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author zhangchao
 * @Date 2019/5/22 15:49
 * @Version v1.0
 */
@AllArgsConstructor
@Data
public class MenuRootOptionsDto {
    private String text;
    private Long value;
}
