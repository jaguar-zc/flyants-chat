package org.flyants.chat.web.v1.app;

import lombok.extern.slf4j.Slf4j;
import org.flyants.chat.domain.entity.platform.message.FriendsApplyRecord;
import org.flyants.chat.domain.service.FriendsService;
import org.flyants.chat.dto.app.FriendsApplyRecordDto;
import org.flyants.chat.dto.app.MessageUserSimpleInfoDto;
import org.flyants.chat.utils.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/6/13 11:42
 * @Version v1.0
 */
@RestController
@RequestMapping(AppVersion.version+"/friends")
@Slf4j
public class AppFriendsController {


    @Autowired
    private FriendsService friendsService;


    @PostMapping("/applyFriends")
    public void applyFriends(String messageUserId){
        String peopleId = JWTManager.get();
        friendsService.applyFriends(peopleId,messageUserId);
    }

    @PostMapping("/handlerFriendsApply")
    public void handlerFriendsApply(String id, FriendsApplyRecord.Status status){
        String peopleId = JWTManager.get();
        friendsService.handlerFriendsApply(peopleId,id,status);
    }

    @GetMapping("/getFriendsApplyList")
    public List<FriendsApplyRecordDto> getFriendsApplyList(){
        String peopleId = JWTManager.get();
        return friendsService.getFriendsApplyList(peopleId);
    }

    @GetMapping("/getFriendsList")
    public List<MessageUserSimpleInfoDto> getFriendsList(){
        String peopleId = JWTManager.get();
        return friendsService.getFriendsList();
    }


}
