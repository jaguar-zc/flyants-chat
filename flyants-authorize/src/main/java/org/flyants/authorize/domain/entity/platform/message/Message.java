package org.flyants.authorize.domain.entity.platform.message;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author zhangchao
 * @Date 2019/5/24 15:22
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class Message {

    public enum MessageType{
        TEXT,IAMGE,AUDIO,VIDEO
    }

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private String conversationId;

    @Column
    private String messageUserId;

    @Column
    private MessageType messageType;

    @Column
    private String body;

    @Column
    private Date sendTime;

    @Column
    private Integer view;

}
