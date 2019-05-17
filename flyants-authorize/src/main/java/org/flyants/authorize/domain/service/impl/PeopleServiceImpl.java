package org.flyants.authorize.domain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.flyants.authorize.domain.repository.PeopleRepository;
import org.flyants.authorize.domain.service.PeopleService;
import org.flyants.authorize.domain.entity.platform.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    private PeopleRepository peopleRepository;


    @Override
    public Optional<People> findPeopleById(Long peopleId) {
        return peopleRepository.findById(peopleId);
    }

    @Override
    public Optional<People> findByUsernameAndPassword(String username, String password) {
        return peopleRepository.findByUsernameAndPassword(username,password);
    }
}
