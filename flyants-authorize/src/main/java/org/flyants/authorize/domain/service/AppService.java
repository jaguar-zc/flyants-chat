package org.flyants.authorize.domain.service;

import org.flyants.authorize.domain.entity.oauth2.OAuthClient;
import org.springframework.data.domain.Page;

/**
 * @Author zhangchao
 * @Date 2019/5/16 16:03
 * @Version v1.0
 */
public interface AppService {
    Page<OAuthClient> findList(Integer page);

    void save(  OAuthClient oAuthClient);
}
