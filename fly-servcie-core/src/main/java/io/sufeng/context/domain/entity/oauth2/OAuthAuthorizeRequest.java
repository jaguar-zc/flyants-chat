package io.sufeng.context.domain.entity.oauth2;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author zhangchao
 * @Date 2019/4/25 15:01
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class OAuthAuthorizeRequest {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private Date creationDate;

    @Column
    private Date modificationDate;

    /**
     * 0: 未知
     * 1: 拒绝
     * 2: 同意
     */
    @Column
    private Integer authState;

    /**
     * 0 未使用
     * 1 已使用
     */
    @Column
    private Integer status;

    @Column
    private String userId;

    @Column
    private String authorizationCode;

    @Column
    private String redirectUri;

    @Column
    private String responseType;

    @Column
    private String state;

    @Column
    private String clientId;


}
