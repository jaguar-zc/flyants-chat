package org.flyants.authorize;

import org.flyants.authorize.domain.repository.ClientRepository;
import org.flyants.authorize.domain.repository.OAuthClientResourceRepository;
import org.flyants.authorize.domain.repository.PeopleRepository;
import org.flyants.authorize.domain.entity.oauth2.OAuthClient;
import org.flyants.authorize.domain.entity.oauth2.OAuthClientResource;
import org.flyants.authorize.domain.entity.platform.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @Author zhangchao
 * @Date 2019/4/26 11:36
 * @Version v1.0
 */
@Component
public class StartListenner {


    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    OAuthClientResourceRepository oAuthClientResourceRepository;

    @PostConstruct
    public void run(){


        for (int i = 0; i < 50; i++) {
            People people = new People();
            people.setCreationDate(new Date());
            people.setModificationDate(new Date());
            people.setEncodedPrincipal("https://thirdqq.qlogo.cn/g?b=sdk&k=XC5OAkdV3Kg0srWxwKPVJg&s=100&t=1556270245");
            people.setUsername("root_"+i);
            people.setPassword("root");

            peopleRepository.save(people);



            OAuthClient client = new OAuthClient();
            client.setClientId("1F23FE23FR"+i);
            client.setClientName("测试商户号"+i);
            client.setClientSecret("8DD427C1B6F047C9BB04E7ECEC5DB710"+i);
            client.setContactEmail("test@sina.com");
            client.setClientIcon("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3060159114,959845824&fm=26&gp=0.jpg");
            client.setContactName("测试商户号");
            client.setDescription("这是一个用于测试的商户号");
            client.setClientRedirectUriHost("http://88.88.10.82:8080/clientserver/callback");
            client.setClientServerDomain("http://www.flyants.org");
            client.setStatus(0);
            client.setPeople(people);

            OAuthClientResource resource = new OAuthClientResource();
            resource.setClientId(client.getClientId());
            resource.setResource("昵称、头像、手机号");
            client.setOAuthClientResource(resource);

            clientRepository.saveAndFlush(client);
        }






    }


}
