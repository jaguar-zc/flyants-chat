package io.sufeng.context.dto.app;

import io.sufeng.context.configuration.Constents;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.MessageFormat;

/**
 * @Author zhangchao
 * @Date 2019/5/27 17:14
 * @Version v1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageUserSimpleInfoDto {
    private String id;
    private String nickName;
    private String encodedPrincipal;

    public String getSmallEncodedPrincipal(){
        return MessageFormat.format("{0}{1}",encodedPrincipal, Constents.IMAGE_STYLE_96);
    }
}
