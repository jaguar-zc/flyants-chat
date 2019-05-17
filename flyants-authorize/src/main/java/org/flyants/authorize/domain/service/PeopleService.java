package org.flyants.authorize.domain.service;

import org.flyants.authorize.domain.entity.platform.People;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/4/26 14:08
 * @Version v1.0
 */
public interface PeopleService {
    Optional<People> findPeopleById(Long peopleId);

    Optional<People> findByUsernameAndPassword(String username, String password);
}
