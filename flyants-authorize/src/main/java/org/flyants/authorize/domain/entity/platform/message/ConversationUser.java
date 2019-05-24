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
public class ConversationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long messageUserId;//名称  单聊的话需要查询 动态查询

    @Column
    private Integer owner;//会话拥有人

    @Column
    private Long conversationId;

}
