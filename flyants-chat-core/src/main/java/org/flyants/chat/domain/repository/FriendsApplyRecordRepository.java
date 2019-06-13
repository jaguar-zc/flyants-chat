package org.flyants.chat.domain.repository;

import org.flyants.chat.domain.entity.platform.message.FriendsApplyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/6/13 11:40
 * @Version v1.0
 */
public interface FriendsApplyRecordRepository  extends JpaRepository<FriendsApplyRecord,String>, JpaSpecificationExecutor {
    List<FriendsApplyRecord> findAllByMessageUserIdAndOrderByApplyTimeDesc(String messageUserId);
}
