package org.flyants.authorize.domain.entity.platform;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 用户赞
 * @Author zhangchao
 * @Date 2019/5/24 15:04
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class PeopleAssist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long peopleId;//被赞人ID

    @Column
    private Long initiativePeopleId;//点赞人ID

}
