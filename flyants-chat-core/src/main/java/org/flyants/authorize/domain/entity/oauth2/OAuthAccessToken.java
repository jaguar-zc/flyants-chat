package org.flyants.authorize.domain.entity.oauth2;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author zhangchao
 * @Date 2019/4/25 18:15
 * @Version v1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OAuthAccessToken {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private Date creationDate;

    @Column
    private Date modificationDate;

    @Column
    private Integer expires;

    @Column
    private String refreshToken;

    @Column
    private String resourceOwnerId;

    @Column
    private String token;

    @Column
    private String clientId;

}
