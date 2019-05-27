package org.flyants.authorize.dto.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.flyants.authorize.domain.Language;
import org.flyants.authorize.domain.entity.PeopleSex;

import java.util.Date;

/**
 * @Author zhangchao
 * @Date 2019/5/27 17:14
 * @Version v1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeopleSimpleDto {
    private Long id;
    private String nickName;
    private String encodedPrincipal;
}
