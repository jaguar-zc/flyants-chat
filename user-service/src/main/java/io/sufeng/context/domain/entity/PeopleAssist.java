package io.sufeng.context.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private String peopleId;//被赞人ID

    @Column
    private String initiativePeopleId;//点赞人ID

}
