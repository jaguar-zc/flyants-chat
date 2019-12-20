package io.sufeng.context.domain.entity.message;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ConversationBackgroudType type;

    @Column
    private String path;//地址


    @Column
    private String conversationId;



}
