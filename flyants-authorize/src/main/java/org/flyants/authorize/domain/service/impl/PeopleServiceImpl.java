package org.flyants.authorize.domain.service.impl;
import org.flyants.authorize.domain.entity.platform.LoginMethod.LoginType;
import org.flyants.authorize.domain.entity.platform.LoginMethod.LoginMethodStatus;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flyants.authorize.configuration.Constents;
import org.flyants.authorize.configuration.PageResult;
import org.flyants.authorize.domain.Language;
import org.flyants.authorize.domain.entity.PeopleSex;
import org.flyants.authorize.domain.entity.platform.*;
import org.flyants.authorize.domain.entity.platform.message.MessageUser;
import org.flyants.authorize.domain.repository.*;
import org.flyants.authorize.domain.service.OssObjectServie;
import org.flyants.authorize.domain.service.PeopleService;
import org.flyants.authorize.domain.service.VerificationService;
import org.flyants.authorize.dto.app.PeopleInfoDto;
import org.flyants.authorize.dto.app.SettingPasswordDto;
import org.flyants.common.exception.BusinessException;
import org.flyants.common.file.ObjectManagerFactory;
import org.flyants.common.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

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
    OssObjectServie ossObjectServie;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private LoginMethodRepository loginMethodRepository;

    @Autowired
    private TokenRepository tokenRepository;


    @Autowired
    private MessageUserRepository messageUserRepository;

    @Autowired
    PeopleIntroductionRepository peopleIntroductionRepository;
    @Autowired
    PeopleAssistRepository peopleAssistRepository;

    @Autowired
    VerificationService verificationService;


    @Override
    public Optional<People> findPeopleById(String peopleId) {
        return peopleRepository.findById(peopleId);
    }

    @Override
    public Optional<String> findByPhone(String phone) {
        Optional<LoginMethod> loginMethod = loginMethodRepository.findByTypeAndMark(LoginMethod.LoginType.PHONE, phone);
        if (!loginMethod.isPresent()) {
            throw new BusinessException("手机号不存在");
        }
        return getLoginToken(loginMethod);
    }

    @Override
    public Optional<String> loginByPassword(String phone, String password) {
        Optional<LoginMethod> loginMethod = loginMethodRepository.findByTypeAndMark(LoginMethod.LoginType.PASSWORD, LoginMethod.buildPasswordMark(phone, password));
        if (!loginMethod.isPresent()) {
            throw new BusinessException("用户名或者密码错误");
        }
        return getLoginToken(loginMethod);
    }

    @Override
    public void logout(String peopleId) {
        Optional<Token> tokenOptional = tokenRepository.findByPeopleId(peopleId);
        if (tokenOptional.isPresent()) {
            tokenRepository.deleteById(tokenOptional.get().getId());
        }
    }

    private Optional<String> getLoginToken(Optional<LoginMethod> loginMethod) {
        String peopleId = loginMethod.get().getPeopleId();
        Token token = new Token();
        token.setCreateTime(new Date());
        token.setExpire(3000);
        token.setAccessToken(UUID.randomUUID().toString());
        token.setRefreshToken(UUID.randomUUID().toString());
        token.setPeopleId(peopleId);
        tokenRepository.save(token);
        return Optional.ofNullable(token.getAccessToken());
    }

    @Override
    public Optional<People> findByPassword(String phone, String password) {
        Optional<LoginMethod> loginMethod = loginMethodRepository.findByTypeAndMark(LoginMethod.LoginType.PASSWORD, LoginMethod.buildPasswordMark(phone, password));
        if (!loginMethod.isPresent()) {
            throw new BusinessException("用户名或者密码错误");
        }
        return peopleRepository.findById(loginMethod.get().getPeopleId());
    }


    @Override
    public PageResult<People> findList(Integer page, Integer size, String searchBy, String keyWord) {
        PageRequest of = PageRequest.of(page - 1, size);
        Specification specification = JpaSpecification.getSpecification(searchBy, keyWord);
        Page<People> all = peopleRepository.findAll(specification, of);

        return new PageResult<People>(all.getTotalElements(), all.getContent());
    }

    @Override
    public void createPeople(String phone, String nickName) {

        if (peopleRepository.findByPhone(phone).isPresent()) {
            throw new BusinessException("手机号已存在");
        }

        People people = new People();
        people.setCreateTime(new Date());
        people.setUpdateTime(new Date());
        people.setEncodedPrincipal("");
        people.setNickName(nickName);
        people.setPhone(phone);
        people.setSex(PeopleSex.UNKNOWN);
        people.setLanguage(Language.zh_CN);
        people.setCountry("");
        people.setProvince("");
        people.setCity("");
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
        pwdLogin.setMark(LoginMethod.buildPasswordMark(people.getPhone(), Constents.DEFAUTL_PASSWORD));
        pwdLogin.setPeopleId(people.getId());
        pwdLogin.setStatus(LoginMethod.LoginMethodStatus.ACTIVE);
        loginMethodSet.add(pwdLogin);

        loginMethodRepository.save(phoneLogin);
        loginMethodRepository.save(pwdLogin);

        String path = ossObjectServie.generateIcon("headimg", nickName);
        people.setEncodedPrincipal(path);
        peopleRepository.saveAndFlush(people);

        //保存消息用户
        MessageUser messageUser = new MessageUser();
        messageUser.setPeopleId(people.getId());
        messageUser.setNickName(people.getNickName());
        messageUser.setEncodedPrincipal(people.getEncodedPrincipal());
        messageUserRepository.save(messageUser);

    }


    @Override
    public PeopleInfoDto info(String peopleId) {
        PeopleInfoDto peopleInfo = new PeopleInfoDto();


        Optional<People> optionalPeople = peopleRepository.findById(peopleId);
        if (!optionalPeople.isPresent()) {
            throw new BusinessException("用户名不存在");
        }
        People people = optionalPeople.get();

        peopleInfo.setId(people.getId());
        peopleInfo.setPeopleNo(people.getPeopleNo());
        peopleInfo.setCreateTime(people.getCreateTime());
        peopleInfo.setEncodedPrincipal(people.getEncodedPrincipal());
        peopleInfo.setNickName(people.getNickName());
        peopleInfo.setPhone(people.getPhone());
        peopleInfo.setSex(people.getSex());
        peopleInfo.setLanguage(people.getLanguage());
        peopleInfo.setCountry(people.getCountry());
        peopleInfo.setProvince(people.getProvince());
        peopleInfo.setCity(people.getCity());
        peopleInfo.setIntroduction("");

        Optional<PeopleIntroduction> peopleIntroduction = peopleIntroductionRepository.findByPeopleIdAndStatus(peopleId, 1);
        if(peopleIntroduction.isPresent()){
            peopleInfo.setIntroduction(peopleIntroduction.get().getIntroduction());
        }

        int peopleAssistCount = peopleAssistRepository.countByPeopleId(peopleId);
        peopleInfo.setPeopleAssistCount(peopleAssistCount);

        return peopleInfo;
    }


    @Override
    public void editPeopleIntroduction(String peopleId, String introduction) {
        if(StringUtils.isEmpty(introduction)){
            return;
        }
        if(peopleId == null){
            throw new BusinessException("用户不存在");
        }

        Optional<PeopleIntroduction> repositoryByPeopleIdAndStatus = peopleIntroductionRepository.findByPeopleIdAndStatus(peopleId, 1);
        if(repositoryByPeopleIdAndStatus.isPresent()){
            PeopleIntroduction peopleIntroduction = repositoryByPeopleIdAndStatus.get();
            peopleIntroduction.setStatus(0);
            peopleIntroductionRepository.saveAndFlush(peopleIntroduction);
        }

        PeopleIntroduction peopleIntroduction = new PeopleIntroduction();
        peopleIntroduction.setCreateTime(new Date());
        peopleIntroduction.setPeopleId(peopleId);
        peopleIntroduction.setIntroduction(introduction);
        peopleIntroduction.setStatus(1);
        peopleIntroductionRepository.save(peopleIntroduction);
    }

    @Override
    public void assistPeople(String peopleId, String assistPeopleId) {
        if (peopleAssistRepository.countByPeopleIdAndInitiativePeopleId(assistPeopleId,peopleId) >0) {
            return;
        }
        PeopleAssist peopleAssist = new PeopleAssist();
        peopleAssist.setInitiativePeopleId(peopleId);
        peopleAssist.setPeopleId(assistPeopleId);
        peopleAssistRepository.saveAndFlush(peopleAssist);
    }

    @Override
    public void setPassword(String peopleId, SettingPasswordDto settingPassword) {
        People people = peopleRepository.findById(peopleId).get();
        verificationService.check(people.getPhone(),settingPassword.getSmsCode());

        Optional<LoginMethod> loginMethodOptional = loginMethodRepository.findByTypeAndPeopleId(LoginMethod.LoginType.PASSWORD, people.getId());

        if(loginMethodOptional.isPresent()){
            LoginMethod loginMethod = loginMethodOptional.get();
            loginMethod.setMark(LoginMethod.buildPasswordMark(people.getPhone(),settingPassword.getPassword()));
            loginMethodRepository.saveAndFlush(loginMethod);
        }else{
            LoginMethod loginMethod = new LoginMethod();
            loginMethod.setType(LoginType.PASSWORD);
            loginMethod.setMark(LoginMethod.buildPasswordMark(people.getPhone(),settingPassword.getPassword()));
            loginMethod.setPeopleId(people.getId());
            loginMethod.setStatus(LoginMethodStatus.ACTIVE);
            loginMethodRepository.saveAndFlush(loginMethod);
        }
    }

    @Override
    public void updatePeopleInfo(String peopleId, PeopleInfoDto peopleInfoDto) {
        Optional<People> optionalPeople = peopleRepository.findById(peopleId);
        if (!optionalPeople.isPresent()) {
            throw new BusinessException("用户名不存在");
        }
        People people = optionalPeople.get();

        if(StringUtils.isNotEmpty(peopleInfoDto.getPeopleNo())){
            people.setPeopleNo(peopleInfoDto.getPeopleNo());
        }

        if(StringUtils.isNotEmpty(peopleInfoDto.getEncodedPrincipal())){
            people.setEncodedPrincipal(peopleInfoDto.getEncodedPrincipal());
            MessageUser messageUser = messageUserRepository.findByPeopleId(people.getId());
            messageUser.setEncodedPrincipal(people.getEncodedPrincipal());
            messageUserRepository.saveAndFlush(messageUser);
        }

        if(StringUtils.isNotEmpty(peopleInfoDto.getNickName())) {
            people.setNickName(peopleInfoDto.getNickName());
            MessageUser messageUser = messageUserRepository.findByPeopleId(people.getId());
            messageUser.setNickName(people.getNickName());
            messageUserRepository.saveAndFlush(messageUser);
        }

        if(StringUtils.isNotEmpty(peopleInfoDto.getPhone())){
            people.setPhone(peopleInfoDto.getPhone());
        }
        if(StringUtils.isNotEmpty(peopleInfoDto.getCity())) {
            people.setCity(peopleInfoDto.getCity());
        }
        if(StringUtils.isNotEmpty(peopleInfoDto.getCountry())) {
            people.setCountry(peopleInfoDto.getCountry());
        }
        if(StringUtils.isNotEmpty(peopleInfoDto.getProvince())) {
            people.setProvince(peopleInfoDto.getProvince());
        }
        if(peopleInfoDto.getLanguage() != null) {
            people.setLanguage(peopleInfoDto.getLanguage());
        }

        if( peopleInfoDto.getSex() != null) {
            people.setSex(peopleInfoDto.getSex());
        }
        peopleRepository.saveAndFlush(people);
    }
}
