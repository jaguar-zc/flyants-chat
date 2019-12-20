package io.sufeng.context.domain.repository;

import io.sufeng.context.domain.entity.oauth2.OAuthUserAuthorize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/4/26 14:01
 * @Version v1.0
 */
@Repository
public interface OAuthUserAuthorizeRepository extends JpaRepository<OAuthUserAuthorize,String> {

    Optional<OAuthUserAuthorize> findByClientIdAndOauthUserId(String clientId,String oauthUserId);
    Optional<OAuthUserAuthorize> findByClientIdAndUserId(String clientId,String userId);
}
