package org.flyants.authorize.domain.repository;

import org.flyants.authorize.oauth2.OAuthClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/4/25 15:33
 * @Version v1.0
 */
@Repository
public interface ClientRepository extends JpaRepository<OAuthClient,String> {

    Optional<OAuthClient> findByClientIdAndClientSecret(String clientId, String clientSecret);

    Optional<OAuthClient> findByClientIdAndClientRedirectUriHost(String clientId,String clientRedirectUriHost);

}
