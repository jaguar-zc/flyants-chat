package io.sufeng.context.domain.repository;

import io.sufeng.context.domain.entity.PeopleAssist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author zhangchao
 * @Date 2019/4/25 15:33
 * @Version v1.0
 */
@Repository
public interface PeopleAssistRepository extends JpaRepository<PeopleAssist,String> {


    int countByPeopleId(String peopleId);


    int countByPeopleIdAndInitiativePeopleId(String peopleId,String initiativePeopleId);

}
