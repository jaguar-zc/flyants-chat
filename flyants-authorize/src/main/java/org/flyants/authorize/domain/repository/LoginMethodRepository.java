package org.flyants.authorize.domain.repository;

import org.flyants.authorize.domain.entity.platform.LoginMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/5/22 9:32
 * @Version v1.0
 */
@Repository
public interface LoginMethodRepository extends JpaRepository<LoginMethod,Long> {

    public Optional<LoginMethod> findByTypeAndMark(LoginMethod.LoginType loginType,String mark);

}
