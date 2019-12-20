package io.sufeng.context.domain.repository;

import io.sufeng.context.domain.entity.PeopleConfig;
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
