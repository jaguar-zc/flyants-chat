package io.sufeng.context.domain.repository;

import io.sufeng.context.domain.entity.message.FriendsApplyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/6/13 11:40
 * @Version v1.0
 */
@Repository
public interface FriendsApplyRecordRepository  extends JpaRepository<FriendsApplyRecord,String>, JpaSpecificationExecutor {
    List<FriendsApplyRecord> findAllByMessageUserIdOrderByApplyTimeDesc(String messageUserId);
}
