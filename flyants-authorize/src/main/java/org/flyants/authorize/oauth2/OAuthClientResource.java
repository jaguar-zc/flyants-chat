package org.flyants.authorize.oauth2;

import lombok.Getter;
import lombok.Setter;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String clientId;

    @Column
    private String resource;

}
