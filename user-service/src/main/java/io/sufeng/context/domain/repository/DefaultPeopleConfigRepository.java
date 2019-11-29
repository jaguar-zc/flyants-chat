package io.sufeng.context.domain.repository;

import io.sufeng.context.domain.entity.system.DefaultPeopleConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author zhangchao
 * @Date 2019/6/6 11:39
 * @Version v1.0
 */
@Repository
public interface DefaultPeopleConfigRepository extends JpaRepository<DefaultPeopleConfig,String> {
}
