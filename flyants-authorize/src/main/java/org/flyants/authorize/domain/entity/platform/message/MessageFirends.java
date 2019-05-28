package org.flyants.authorize.domain.entity.platform.message;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 我的好友
 * @Author zhangchao
 * @Date 2019/5/24 16:33
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class MessageFirends {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private String myMessageUserId;

    @Column
    private String firendsMessageUserId;

}
