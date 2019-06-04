package org.flyants.chat.domain.repository;

import org.flyants.chat.domain.entity.oauth2.OAuthClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/4/25 15:33
 * @Version v1.0
 */
@Repository
public interface OAuthClientRepository extends JpaRepository<OAuthClient,String> , JpaSpecificationExecutor {

    Optional<OAuthClient> findByClientIdAndClientSecret(String clientId, String clientSecret);

    Optional<OAuthClient> findByClientIdAndClientRedirectUriHost(String clientId,String clientRedirectUriHost);

}
