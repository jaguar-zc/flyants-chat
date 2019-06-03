package org.flyants.authorize.domain.repository;

import org.flyants.authorize.domain.entity.platform.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/4/25 15:33
 * @Version v1.0
 */
@Repository
public interface TokenRepository extends JpaRepository<Token,String> {

    Optional<Token> findByAccessToken(String accessToken);

    Optional<Token> findByRefreshToken(String refreshToken);

    Optional<Token> findByPeopleId(String peopleId);
}
