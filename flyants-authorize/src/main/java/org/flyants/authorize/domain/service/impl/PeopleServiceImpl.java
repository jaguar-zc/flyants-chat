package org.flyants.authorize.domain.service.impl;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.flyants.authorize.configuration.PageResult;
import org.flyants.authorize.domain.Language;
import org.flyants.authorize.domain.entity.oauth2.OAuthClient;
import org.flyants.authorize.domain.entity.platform.LoginMethod;
import org.flyants.authorize.domain.entity.platform.Token;
import org.flyants.authorize.domain.entity.platform.message.MessageUser;
import org.flyants.authorize.domain.repository.LoginMethodRepository;
import org.flyants.authorize.domain.repository.MessageUserRepository;
import org.flyants.authorize.domain.repository.PeopleRepository;
import org.flyants.authorize.domain.repository.TokenRepository;
import org.flyants.authorize.domain.service.PeopleService;
import org.flyants.authorize.domain.entity.platform.People;
import org.flyants.common.exception.BusinessException;
import org.flyants.common.file.ObjectManagerFactory;
import org.flyants.common.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @Author zhangchao
 * @Date 2019/4/26 14:08
 * @Version v1.0
 */
@Service
@Slf4j
@Transactional
public class PeopleServiceImpl implements PeopleService {


    @Autowired
    ObjectManagerFactory objectManagerFactory;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private LoginMethodRepository loginMethodRepository;

    @Autowired
    private TokenRepository tokenRepository;


    @Autowired
    private MessageUserRepository messageUserRepository;


    @Override
    public Optional<People> findPeopleById(Long peopleId) {
        return peopleRepository.findById(peopleId);
    }

    @Override
    public Optional<People> findByPhone(String phone ) {
        return peopleRepository.findByPhone(phone);
    }

    @Override
    public Optional<String> loginByPassword(String phone, String password) {
        Optional<LoginMethod> loginMethod = loginMethodRepository.findByTypeAndMark(LoginMethod.LoginType.PASSWORD, LoginMethod.buildPasswordMark(phone, password));
        if(!loginMethod.isPresent()){
            throw new BusinessException("用户名或者密码错误");
        }

        Long peopleId = loginMethod.get().getPeopleId();
        Token token = new Token();
        token.setCreateTime(new Date());
        token.setExpire(3000);
        token.setAccessToken(UUID.randomUUID().toString());
        token.setRefreshToken(UUID.randomUUID().toString());
        token.setPeopleId(peopleId);
        tokenRepository.save(token);

        return Optional.of(token.getAccessToken());
    }

    @Override
    public Optional<People> findByPassword(String phone, String password) {
        Optional<LoginMethod> loginMethod = loginMethodRepository.findByTypeAndMark(LoginMethod.LoginType.PASSWORD, LoginMethod.buildPasswordMark(phone, password));
        if(!loginMethod.isPresent()){
            throw new BusinessException("用户名或者密码错误");
        }
        return peopleRepository.findById(loginMethod.get().getPeopleId());
    }


    @Override
    public PageResult<People> findList(Integer page, Integer size, String searchBy, String keyWord) {
        Page<People> all = peopleRepository.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> pr = new ArrayList<>();
                if(!StringUtils.isEmpty(searchBy)){
                    pr.add(cb.like(root.get(searchBy).as(String.class),"%"+keyWord+"%"));
                }
                return cb.and(pr.toArray(new Predicate[pr.size()]));
            }
        }, PageRequest.of(page - 1, size));

        return new PageResult<People>(all.getTotalElements(), all.getContent());
    }

    @Override
    public void createPeople(String phone,String nickName) {


        if (peopleRepository.findByPhone(phone).isPresent()) {
            throw new BusinessException("手机号已存在");
        }

        People people = new People();
        people.setCreationDate(new Date());
        people.setModificationDate(new Date());
        people.setEncodedPrincipal("");
        people.setNickName(nickName);
        people.setPhone(phone);
        people.setSex(0);
        people.setLanguage(Language.zh_CN);
        people.setCountry("中国");
        people.setProvince("北京市");
        people.setCity("望江区");
        people = peopleRepository.saveAndFlush(people);


        List<LoginMethod> loginMethodSet = new ArrayList<>();

        LoginMethod phoneLogin = new LoginMethod();
        phoneLogin.setType(LoginMethod.LoginType.PHONE);
        phoneLogin.setMark(people.getPhone());
        phoneLogin.setPeopleId(people.getId());
        phoneLogin.setStatus(LoginMethod.LoginMethodStatus.ACTIVE);

        loginMethodSet.add(phoneLogin);

        LoginMethod pwdLogin = new LoginMethod();
        pwdLogin.setType(LoginMethod.LoginType.PASSWORD);
        pwdLogin.setMark(LoginMethod.buildPasswordMark(people.getPhone(),"123456"));
        pwdLogin.setPeopleId(people.getId());
        pwdLogin.setStatus(LoginMethod.LoginMethodStatus.ACTIVE);
        loginMethodSet.add(pwdLogin);

        loginMethodRepository.save(phoneLogin);
        loginMethodRepository.save(pwdLogin);


        //保存消息用户
        MessageUser messageUser = new MessageUser();
        messageUser.setPeopleId(people.getId());
        messageUserRepository.save(messageUser);

        String imgName = "headimg/"+UUID.randomUUID().toString()+".jpg";
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageUtil.generateImg(nickName,byteArrayOutputStream);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            String path = objectManagerFactory.upload(inputStream, imgName);
            people.setEncodedPrincipal(path);
            peopleRepository.saveAndFlush(people);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
