package org.flyants.chat.domain.entity.oauth2;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 客户端需要的资源
 * @Author zhangchao
 * @Date 2019/4/25 16:10
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class OAuthClientResource {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private String clientId;

    @Column
    private String resource;

}
