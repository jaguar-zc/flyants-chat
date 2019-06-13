package org.flyants.chat.domain.service.impl;
import java.util.Date;
import org.flyants.chat.domain.entity.platform.message.FriendsApplyRecord.Status;

import org.flyants.chat.domain.entity.platform.message.FriendsApplyRecord;
import org.flyants.chat.domain.entity.platform.message.MessageFirends;
import org.flyants.chat.domain.entity.platform.message.MessageUser;
import org.flyants.chat.domain.repository.FriendsApplyRecordRepository;
import org.flyants.chat.domain.repository.MessageFirendsRepository;
import org.flyants.chat.domain.repository.MessageUserRepository;
import org.flyants.chat.domain.service.FriendsService;
import org.flyants.chat.dto.app.FriendsApplyRecordDto;
import org.flyants.chat.dto.app.MessageUserSimpleInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author zhangchao
 * @Date 2019/6/13 11:41
 * @Version v1.0
 */
@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    FriendsApplyRecordRepository friendsApplyRecordRepository;

    @Autowired
    MessageFirendsRepository messageFirendsRepository;

    @Autowired
    MessageUserRepository messageUserRepository;

    @Override
    public void applyFriends(String peopleId,String messageUserId) {

        MessageUser messageUser = messageUserRepository.findByPeopleId(peopleId);
        FriendsApplyRecord friendsApplyRecord = new FriendsApplyRecord();
        friendsApplyRecord.setApplyMessageUserId(messageUser.getId());
        friendsApplyRecord.setMessageUserId(messageUserId);
        friendsApplyRecord.setApplyTime(new Date());
        friendsApplyRecord.setStatus(Status.APPLY);
        friendsApplyRecordRepository.save(friendsApplyRecord);

        // todo  推送消息给  friendsApplyRecord.getMessageUserId()
    }

    @Override
    public void handlerFriendsApply(String peopleId,String id, FriendsApplyRecord.Status status) {
        Optional<FriendsApplyRecord> friendsApplyRecordOptional = friendsApplyRecordRepository.findById(id);
        if(friendsApplyRecordOptional.isPresent()){
            FriendsApplyRecord friendsApplyRecord = friendsApplyRecordOptional.get();
            friendsApplyRecord.setStatus(status);
            friendsApplyRecord.setHandlerTime(new Date());
            friendsApplyRecordRepository.saveAndFlush(friendsApplyRecord);
            // todo  推送消息给  friendsApplyRecord.getApplyMessageUserId()
        }
    }

    @Override
    public List<MessageUserSimpleInfoDto> getFriendsList(String peopleId) {
        MessageUser messageUser = messageUserRepository.findByPeopleId(peopleId);
        List<MessageFirends> messageFirends = messageFirendsRepository.findAllByMyMessageUserId(messageUser.getId());

        return messageFirends.stream().map(item ->{
            MessageUser friendsMessageUser = messageUserRepository.findByPeopleId(item.getFirendsMessageUserId());
            return new MessageUserSimpleInfoDto(friendsMessageUser.getId(),friendsMessageUser.getNickName(),friendsMessageUser.getEncodedPrincipal());
        }).collect(Collectors.toList());
    }


    @Override
    public List<FriendsApplyRecordDto> getFriendsApplyList(String peopleId) {
        MessageUser messageUser = messageUserRepository.findByPeopleId(peopleId);

        List<FriendsApplyRecord> list = friendsApplyRecordRepository.findAllByMessageUserIdAndOrderByApplyTimeDesc(messageUser.getId());
        List<FriendsApplyRecordDto> collect = list.stream().map(item -> {

            MessageUser applyMessageUser = messageUserRepository.findByPeopleId(item.getApplyMessageUserId());

            FriendsApplyRecordDto record = new FriendsApplyRecordDto();
            record.setId(item.getId());
            record.setApplyMessageUserId(item.getApplyMessageUserId());
            record.setApplyMessageNickName(applyMessageUser.getNickName());
            record.setApplyMessageEncodedPrincipal(applyMessageUser.getEncodedPrincipal());
            record.setMessageUserId(item.getMessageUserId());
            record.setApplyTime(item.getApplyTime());
            record.setHandlerTime(item.getHandlerTime());
            record.setStatus(item.getStatus().name());
            return record;
        }).collect(Collectors.toList());
        return collect;
    }
}
