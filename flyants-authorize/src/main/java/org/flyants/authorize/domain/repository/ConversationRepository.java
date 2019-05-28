package org.flyants.authorize.domain.repository;

import org.flyants.authorize.domain.entity.platform.message.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author zhangchao
 * @Date 2019/4/25 15:33
 * @Version v1.0
 */
@Repository
public interface ConversationRepository extends JpaRepository<Conversation,String> {

}
