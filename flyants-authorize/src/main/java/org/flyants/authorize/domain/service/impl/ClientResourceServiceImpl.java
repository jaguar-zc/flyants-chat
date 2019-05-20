package org.flyants.authorize.domain.service.impl;

import org.flyants.authorize.domain.entity.oauth2.OAuthClientResource;
import org.flyants.authorize.domain.repository.OAuthClientResourceRepository;
import org.flyants.authorize.domain.service.ClientResourceService;
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
