package org.flyants.authorize.domain.entity.platform.dynamic;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author zhangchao
 * @Date 2019/5/24 15:15
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class DynamicAssist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date createTime;

    @Column
    private Long dynamicId;

    @Column
    private Long peopleId;

}
