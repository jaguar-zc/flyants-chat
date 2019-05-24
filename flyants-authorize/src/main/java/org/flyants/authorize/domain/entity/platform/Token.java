package org.flyants.authorize.domain.entity.platform;

import lombok.Getter;
import lombok.Setter;
import org.flyants.authorize.domain.Language;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date createTime;

    @Column
    private Integer expire;

    @Column
    private String accessToken;

    @Column
    private String refreshToken;

    @Column
    private Long peopleId;

}
