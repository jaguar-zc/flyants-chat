package org.flyants.chat.domain.repository;

import org.flyants.chat.domain.entity.oauth2.OAuthAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/4/25 15:33
 * @Version v1.0
 */
@Repository
public interface OAuthAccessTokenRepository extends JpaRepository<OAuthAccessToken,String> {


    Optional<OAuthAccessToken> findByToken(String token);

}
