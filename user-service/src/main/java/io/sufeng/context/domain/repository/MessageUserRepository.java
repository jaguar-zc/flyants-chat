package io.sufeng.context.domain.repository;

import io.sufeng.context.domain.entity.message.MessageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author zhangchao
 * @Date 2019/4/25 15:33
 * @Version v1.0
 */
@Repository
public interface MessageUserRepository extends JpaRepository<MessageUser,String> {

    MessageUser findByPeopleId(String peopleId);
}
