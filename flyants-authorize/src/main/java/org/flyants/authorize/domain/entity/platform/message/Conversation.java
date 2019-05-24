package org.flyants.authorize.domain.entity.platform.message;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author zhangchao
 * @Date 2019/5/24 15:17
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class Conversation {

    public enum ConversationType{
        SINGLE,GROUP
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ConversationType type;

    @Column
    private String name;//名称  单聊的话需要查询 动态查询

    @Column
    private String icon;//图标  单聊的话需要查询 动态查询

    @Column
    private String tags;//标签

    @Column
    private Integer top;//置顶

    @Column
    private Integer DontDisturb;//免打扰

    @Column
    private Long messageUserId;



}
