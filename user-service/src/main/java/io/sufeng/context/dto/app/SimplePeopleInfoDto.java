package io.sufeng.context.dto.app;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author zhangchao
 * @Date 2019/6/6 13:46
 * @Version v1.0
 */
@Getter
@Setter
public class SimplePeopleInfoDto {

    private String id;
    private String peopleNo;
    private String encodedPrincipal;
    private String nickName;
    private String location;

}
