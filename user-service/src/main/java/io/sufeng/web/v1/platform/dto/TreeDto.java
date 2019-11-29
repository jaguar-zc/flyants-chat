package io.sufeng.web.v1.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/5/22 16:47
 * @Version v1.0
 */
@AllArgsConstructor
@Getter
@Setter
public class TreeDto {
    private String title;
    private String key;
    private Boolean isLeaf;
    private List<TreeDto> children;
}
