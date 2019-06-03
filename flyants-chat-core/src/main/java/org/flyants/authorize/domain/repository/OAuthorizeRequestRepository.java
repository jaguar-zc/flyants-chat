package org.flyants.authorize.domain.repository;

import org.flyants.authorize.domain.entity.oauth2.OAuthAuthorizeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/4/25 15:33
 * @Version v1.0
 */
@Repository
public interface OAuthorizeRequestRepository extends JpaRepository<OAuthAuthorizeRequest,String> {

    Optional<OAuthAuthorizeRequest> findByAuthorizationCode(String authorizationCode);
}
