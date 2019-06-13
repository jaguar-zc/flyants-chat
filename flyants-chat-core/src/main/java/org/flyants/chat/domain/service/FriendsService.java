package org.flyants.chat.domain.service;

import org.flyants.chat.domain.entity.platform.message.FriendsApplyRecord;
import org.flyants.chat.dto.app.FriendsApplyRecordDto;
import org.flyants.chat.dto.app.MessageUserSimpleInfoDto;

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
