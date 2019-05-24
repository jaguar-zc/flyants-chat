package org.flyants.authorize.domain.entity.platform;

import lombok.Getter;
import lombok.Setter;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Long parentId;//父菜单ID
}
