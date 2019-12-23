package io.sufeng.context.domain.service.impl;

import io.sufeng.context.domain.entity.message.FriendsApplyRecord;
import io.sufeng.context.domain.entity.message.FriendsApplyRecord.Status;
import io.sufeng.context.domain.entity.message.MessageFirends;
import io.sufeng.context.domain.entity.message.MessageUser;
import io.sufeng.context.domain.repository.FriendsApplyRecordRepository;
import io.sufeng.context.domain.repository.MessageFirendsRepository;
import io.sufeng.context.domain.repository.MessageUserRepository;
import io.sufeng.context.domain.service.FriendsService;
import io.sufeng.context.dto.app.FriendsApplyRecordDto;
import io.sufeng.context.dto.app.MessageUserSimpleInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
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
        MessageUser messageUser = messageUserRepository.findById(peopleId).get();
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

            Assert.isTrue(friendsApplyRecord.getStatus() == Status.APPLY,"请求已处理");

            friendsApplyRecord.setStatus(status);
            friendsApplyRecord.setHandlerTime(new Date());
            friendsApplyRecordRepository.saveAndFlush(friendsApplyRecord);
            // todo  推送消息给  friendsApplyRecord.getApplyMessageUserId()

            if(status == Status.AGREE ){
                if(!messageFirendsRepository.existsByMyPeopleIdAndFirendsPeopleId(friendsApplyRecord.getApplyMessageUserId(),friendsApplyRecord.getMessageUserId())){
                    MessageFirends messageFirends = new MessageFirends();
                    messageFirends.setMyPeopleId(friendsApplyRecord.getApplyMessageUserId());
                    messageFirends.setFirendsPeopleId(friendsApplyRecord.getMessageUserId());
                    messageFirendsRepository.save(messageFirends);
                }
                if(!messageFirendsRepository.existsByMyPeopleIdAndFirendsPeopleId(friendsApplyRecord.getMessageUserId(),friendsApplyRecord.getApplyMessageUserId())){
                    MessageFirends messageFirends = new MessageFirends();
                    messageFirends.setMyPeopleId(friendsApplyRecord.getMessageUserId());
                    messageFirends.setFirendsPeopleId(friendsApplyRecord.getApplyMessageUserId());
                    messageFirendsRepository.save(messageFirends);
                }
            }
        }
    }

    @Override
    public List<MessageUserSimpleInfoDto> getFriendsList(String peopleId) {
        MessageUser messageUser = messageUserRepository.findById(peopleId).get();
        Assert.notNull(messageUser,"message user is null");
        List<MessageFirends> messageFirends = messageFirendsRepository.findAllByMyPeopleId(messageUser.getId());

        return messageFirends.stream().map(item ->{
            MessageUser friendsMessageUser = messageUserRepository.findById(item.getFirendsPeopleId()).get();
            return new MessageUserSimpleInfoDto(friendsMessageUser.getId(),friendsMessageUser.getNickName(),friendsMessageUser.getEncodedPrincipal() );
        }).collect(Collectors.toList());
    }


    @Override
    public List<FriendsApplyRecordDto> getFriendsApplyList(String peopleId) {
        MessageUser messageUser = messageUserRepository.findById(peopleId).get();

        Assert.notNull(messageUser,"message user is null");

        List<FriendsApplyRecord> list = friendsApplyRecordRepository.findAllByMessageUserIdOrderByApplyTimeDesc(messageUser.getId());
        List<FriendsApplyRecordDto> collect = list.stream().map(item -> {

            MessageUser applyMessageUser = messageUserRepository.findById(item.getApplyMessageUserId()).get();

            FriendsApplyRecordDto record = new FriendsApplyRecordDto();
            record.setId(item.getId());
            record.setApplyUserId(item.getApplyMessageUserId());
            record.setApplyNickName(applyMessageUser.getNickName());
            record.setApplyMessageEncodedPrincipal(applyMessageUser.getEncodedPrincipal());
            record.setPeopleId(item.getMessageUserId());
            record.setApplyTime(item.getApplyTime());
            record.setHandlerTime(item.getHandlerTime());
            record.setStatus(item.getStatus().name());
            return record;
        }).collect(Collectors.toList());
        return collect;
    }
}
