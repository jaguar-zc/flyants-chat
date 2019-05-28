package org.flyants.authorize.domain.entity.platform;

import lombok.Getter;
import lombok.Setter;
import org.flyants.authorize.domain.Language;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/4/25 18:15
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class Token {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private Date createTime;

    @Column
    private Integer expire;

    @Column
    private String accessToken;

    @Column
    private String refreshToken;

    @Column
    private String peopleId;

}
