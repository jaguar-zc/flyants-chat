package io.sufeng.context.dto.app;

import lombok.Getter;
import lombok.Setter;
import io.sufeng.context.domain.DynamicVisibility;

import java.util.List;

/**
 * 动态添加
 * @Author zhangchao
 * @Date 2019/5/24 15:10
 * @Version v1.0
 */
@Getter
@Setter
public class DynamicAddDto {
    private String text;
    private List<String> images;//List<String>
    private String location;//位置
    private DynamicVisibility visibility = DynamicVisibility.ALL;
}
