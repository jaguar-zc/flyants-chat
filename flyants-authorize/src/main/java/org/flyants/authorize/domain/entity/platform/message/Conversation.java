package org.flyants.authorize.domain.entity.platform.message;

import lombok.Getter;
import lombok.Setter;
import org.flyants.authorize.domain.ConversationType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/5/24 15:17
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class Conversation {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

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
    private Integer dontDisturb;//免打扰

    @Column
    private String messageUserId;//会话拥有者

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversationId")
    private List<ConversationUser> conversationUserList;




}
