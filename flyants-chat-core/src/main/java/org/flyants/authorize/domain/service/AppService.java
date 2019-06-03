package org.flyants.authorize.domain.service;

import org.flyants.authorize.configuration.PageResult;
import org.flyants.authorize.domain.entity.oauth2.OAuthClient;

/**
 * @Author zhangchao
 * @Date 2019/5/16 16:03
 * @Version v1.0
 */
public interface AppService {
    PageResult<OAuthClient> findList(Integer page,Integer size,String searchBy,String keyWord);

    void save(  OAuthClient oAuthClient);

    void update(String id, OAuthClient oAuthClient);

    OAuthClient find(String clientId);
}
