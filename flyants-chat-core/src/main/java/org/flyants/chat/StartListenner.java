package org.flyants.chat;

import org.flyants.chat.domain.Language;
import org.flyants.chat.domain.entity.PeopleSex;
import org.flyants.chat.domain.entity.oauth2.OAuthClient;
import org.flyants.chat.domain.entity.oauth2.OAuthClientResource;
import org.flyants.chat.domain.entity.platform.LoginMethod;
import org.flyants.chat.domain.entity.platform.LoginMethod.LoginMethodStatus;
import org.flyants.chat.domain.entity.platform.LoginMethod.LoginType;
import org.flyants.chat.domain.entity.platform.People;
import org.flyants.chat.domain.repository.LoginMethodRepository;
import org.flyants.chat.domain.repository.OAuthClientRepository;
import org.flyants.chat.domain.repository.OAuthClientResourceRepository;
import org.flyants.chat.domain.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/4/26 11:36
 * @Version v1.0
 */
@Component
public class StartListenner {


    @Autowired
    OAuthClientRepository OAuthClientRepository;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    OAuthClientResourceRepository oAuthClientResourceRepository;

    @Autowired
    LoginMethodRepository loginMethodRepository;

    //    @PostConstruct
    public void run() {

        for (int i = 0; i < 50; i++) {
            People people = new People();
            people.setCreateTime(new Date());
            people.setUpdateTime(new Date());
            people.setEncodedPrincipal("https://thirdqq.qlogo.cn/g?b=sdk&k=XC5OAkdV3Kg0srWxwKPVJg&s=100&t=1556270245");
            people.setNickName("root_" + i);
            people.setPhone("130000000" + (i < 10 ? "0" + i : i));
            people.setSex(PeopleSex.UNKNOWN);
            people.setLanguage(Language.zh_CN);
            people.setCountry("中国");
            people.setProvince("北京市");
            people.setCity("望江区");
            people = peopleRepository.saveAndFlush(people);


            List<LoginMethod> loginMethodSet = new ArrayList<>();

            LoginMethod phoneLogin = new LoginMethod();
            phoneLogin.setType(LoginType.PHONE);
            phoneLogin.setMark(people.getPhone());
            phoneLogin.setPeopleId(people.getId());
            phoneLogin.setStatus(LoginMethodStatus.ACTIVE);

            loginMethodSet.add(phoneLogin);

            LoginMethod pwdLogin = new LoginMethod();
            pwdLogin.setType(LoginType.PASSWORD);
            pwdLogin.setMark(LoginMethod.buildPasswordMark(people.getPhone(), "123456"));
            pwdLogin.setPeopleId(people.getId());
            pwdLogin.setStatus(LoginMethodStatus.ACTIVE);
            loginMethodSet.add(pwdLogin);

//            people.setLoginMethodList(loginMethodSet);

            loginMethodRepository.save(phoneLogin);
            loginMethodRepository.save(pwdLogin);


            OAuthClient client = new OAuthClient();
            client.setClientId("1F23FE23FR" + i);
            client.setClientName("测试商户号" + i);
            client.setClientSecret("8DD427C1B6F047C9BB04E7ECEC5DB710" + i);
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

            OAuthClientRepository.saveAndFlush(client);
        }

    }

}
