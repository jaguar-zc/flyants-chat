package io.sufeng.context.domain.service;

import io.sufeng.context.domain.entity.message.FriendsApplyRecord;
import io.sufeng.context.dto.app.FriendsApplyRecordDto;
import io.sufeng.context.dto.app.MessageUserSimpleInfoDto;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/6/13 11:41
 * @Version v1.0
 */
public interface FriendsService {

    void applyFriends(String peopleId,String messageUserId);
    void handlerFriendsApply(String peopleId,String id, FriendsApplyRecord.Status status);
    List<FriendsApplyRecordDto> getFriendsApplyList(String peopleId);
    List<MessageUserSimpleInfoDto> getFriendsList(String peopleId);

}
