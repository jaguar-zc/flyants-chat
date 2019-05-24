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
public class ConversationBackgroud {

    public enum ConversationBackgroudType{
        SYSTEM,USER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ConversationBackgroudType type;

    @Column
    private String path;//地址


    @Column
    private Long conversationId;



}
