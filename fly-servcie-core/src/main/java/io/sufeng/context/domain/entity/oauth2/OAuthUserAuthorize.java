package io.sufeng.context.domain.entity.oauth2;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author zhangchao
 * @Date 2019/4/26 10:34
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class OAuthUserAuthorize {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private String clientId;//客户端id

    @Column
    private String oauthUserId;//绑定账号的id，例如对应wx来说，就是openId

    @Column
    private String userId;//用户ID

    @Column
    private String oauthUserName;//绑定账号的名称

    @Column
    private String authorizeResource;//授权的哪些资源【】
}
