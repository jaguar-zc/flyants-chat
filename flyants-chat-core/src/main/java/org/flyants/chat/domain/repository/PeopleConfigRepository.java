package org.flyants.chat.domain.repository;

import org.flyants.chat.domain.entity.platform.PeopleConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/6/6 11:39
 * @Version v1.0
 */
@Repository
public interface PeopleConfigRepository extends JpaRepository<PeopleConfig,String> {

    Optional<PeopleConfig> findFirstByPeopleId(String peopleId);

}
