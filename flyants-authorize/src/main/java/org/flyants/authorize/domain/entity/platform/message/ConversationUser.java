package org.flyants.authorize.domain.entity.platform.message;

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
public class ConversationUser {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private String messageUserId;//会话用户

    @Column
    private String conversationId;

}
