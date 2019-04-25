package org.flyants.authorize.oauth2;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author zhangchao
 * @Date 2019/4/25 15:01
 * @Version v1.0
 */
@Entity
public class AuthorizeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date creationDate;

    @Column
    private Date modificationDate;

    @Column
    private String authState;

    @Column
    private String authorizationCode;

    @Column
    private String encodedPrincipal;

    @Column
    private String redirectUri;

    @Column
    private String responseType;

    @Column
    private String state;

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

    public String getAuthState() {
        return authState;
    }

    public void setAuthState(String authState) {
        this.authState = authState;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getEncodedPrincipal() {
        return encodedPrincipal;
    }

    public void setEncodedPrincipal(String encodedPrincipal) {
        this.encodedPrincipal = encodedPrincipal;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
