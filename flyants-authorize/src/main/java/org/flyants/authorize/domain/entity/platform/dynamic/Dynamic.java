package org.flyants.authorize.domain.entity.platform.dynamic;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 动态
 * @Author zhangchao
 * @Date 2019/5/24 15:10
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class Dynamic {

    public enum DynamicVisibility{
        ALL,FIRENDS,PRIVATE
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date createTime;

    @Column
    private Long peopleId;

    @Column
    private String text;

    @Column
    private String images;//List<String>

    @Column
    @Enumerated(value = EnumType.STRING)
    private DynamicVisibility visibility;//List<String>




}
