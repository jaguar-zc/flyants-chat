package io.sufeng.context.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author zhangchao
 * @Date 2019/5/22 14:08
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class Menu {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private String icon;

    @Column
    private String name;

    @Column
    private String path;

    @Column
    private String component;

    @Column
    private Integer level;//深度  1级菜单 2级菜单

    @Column
    private String parentId;//父菜单ID
}
