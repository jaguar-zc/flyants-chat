package org.flyants.authorize.domain.entity.oauth2;

import lombok.Getter;
import lombok.Setter;
import org.flyants.authorize.domain.entity.platform.People;

import javax.persistence.*;

/**
 * @Author zhangchao
 * @Date 2019/4/25 16:10
 * @Version v1.0
 */

@Getter
@Setter
@Entity
public class OAuthClient {

    @Id
    @Column
    private String clientId;

    @Column
    private String clientName;

    @Column
    private String clientIcon;

    @Column
    private String clientSecret;

    @Column
    private String contactEmail;

    @Column
    private String contactName;

    @Column
    private String description;

    @Column
    private String clientRedirectUriHost;//重定向域名

    @Column
    private String clientServerDomain;

    @Column
    private Integer status;//状态。0:正常；1:冻结

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "client_resource_id", referencedColumnName = "id")
    private OAuthClientResource oAuthClientResource;


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "people_id", referencedColumnName = "id")
    private People people;



}
