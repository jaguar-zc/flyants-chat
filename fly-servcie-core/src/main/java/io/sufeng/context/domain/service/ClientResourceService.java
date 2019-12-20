package io.sufeng.context.domain.service;

import io.sufeng.context.domain.entity.oauth2.OAuthClientResource;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/5/20 13:43
 * @Version v1.0
 */
public interface ClientResourceService {

    List<OAuthClientResource> findAll();

}
