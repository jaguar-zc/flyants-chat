package org.flyants.authorize.oauth2;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author zhangchao
 * @Date 2019/4/25 18:15
 * @Version v1.0
 */
@Entity
public class AccessToken {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getEncodedPrincipal() {
        return encodedPrincipal;
    }

    public void setEncodedPrincipal(String encodedPrincipal) {
        this.encodedPrincipal = encodedPrincipal;
    }

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getResourceOwnerId() {
        return resourceOwnerId;
    }

    public void setResourceOwnerId(String resourceOwnerId) {
        this.resourceOwnerId = resourceOwnerId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
