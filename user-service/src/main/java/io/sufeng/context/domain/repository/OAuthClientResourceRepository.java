package io.sufeng.context.domain.repository;

import io.sufeng.context.domain.entity.oauth2.OAuthClientResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author zhangchao
 * @Date 2019/4/26 14:01
 * @Version v1.0
 */
@Repository
public interface OAuthClientResourceRepository extends JpaRepository<OAuthClientResource,String> {

}
