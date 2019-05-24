package org.flyants.authorize.domain.entity.platform.message;

import lombok.Getter;
import lombok.Setter;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long myMessageUserId;

    @Column
    private Long firendsMessageUserId;

}
