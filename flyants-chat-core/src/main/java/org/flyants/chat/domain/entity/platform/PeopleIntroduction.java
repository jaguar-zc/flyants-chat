package org.flyants.chat.domain.entity.platform;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 简介
 * @Author zhangchao
 * @Date 2019/5/24 15:06
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class PeopleIntroduction {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private Date createTime;

    @Column
    private String peopleId;

    @Column
    private String introduction;

    @Column
    private Integer status;//0：过期 1：启用
}
