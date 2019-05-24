package org.flyants.authorize.domain.entity.platform.message;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author zhangchao
 * @Date 2019/5/24 15:22
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class MessageUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long peopleId;

    @Column
    private String token;

    @Column
    private String channelId;

    @Column
    private String host;

}
