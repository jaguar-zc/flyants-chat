package io.sufeng.context.domain.repository;

import io.sufeng.context.domain.entity.message.MessageFirends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/4/25 15:33
 * @Version v1.0
 */
@Repository
public interface MessageFirendsRepository extends JpaRepository<MessageFirends,String> {

    List<MessageFirends> findAllByMyPeopleId(String myMessageUserId);

    Boolean existsByMyPeopleIdAndFirendsPeopleId(String myPeopleId,String firendsPeopleId);

}
