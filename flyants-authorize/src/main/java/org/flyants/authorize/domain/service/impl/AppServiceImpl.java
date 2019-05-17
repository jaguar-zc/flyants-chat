package org.flyants.authorize.domain.service.impl;

import org.flyants.authorize.configuration.PageResult;
import org.flyants.authorize.domain.entity.oauth2.OAuthClient;
import org.flyants.authorize.domain.entity.oauth2.OAuthClientResource;
import org.flyants.authorize.domain.repository.ClientRepository;
import org.flyants.authorize.domain.service.AppService;
import org.flyants.authorize.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @Author zhangchao
 * @Date 2019/5/16 16:03
 * @Version v1.0
 */
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    ClientRepository clientRepository;


    @Override
    public PageResult<OAuthClient> findList(Integer page,Integer size) {
        PageRequest of = PageRequest.of(page - 1, size);

        Page<OAuthClient> all = clientRepository.findAll(of);
        return new PageResult<OAuthClient>(all.getTotalElements(), all.getContent());
    }

    @Override
    public void save(OAuthClient client) {
        client.setStatus(0);
        client.setPeople(ResourceUtils.getCurrentPeople());
        OAuthClientResource resource = new OAuthClientResource();
        resource.setClientId(client.getClientId());
        resource.setResource("昵称、头像、手机号");
        client.setOAuthClientResource(resource);
        clientRepository.saveAndFlush(client);
    }
}
