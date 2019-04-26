package org.flyants.authorize.oauth2;

import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date creationDate;

    @Column
    private Date modificationDate;

    @Column
    private String encodedPrincipal;

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
