package io.sufeng.context.domain.service.impl;

import io.sufeng.context.domain.service.ClientResourceService;
import io.sufeng.context.domain.entity.oauth2.OAuthClientResource;
import io.sufeng.context.domain.repository.OAuthClientResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/5/20 13:43
 * @Version v1.0
 */
@Service
public class ClientResourceServiceImpl implements ClientResourceService {

    @Autowired
    OAuthClientResourceRepository clientResourceRepository;

    @Override
    public List<OAuthClientResource> findAll() {
        return clientResourceRepository.findAll();
    }
}
