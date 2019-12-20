package io.sufeng.app.restful.app;

import lombok.extern.slf4j.Slf4j;
import io.sufeng.context.domain.entity.message.FriendsApplyRecord;
import io.sufeng.context.domain.service.FriendsService;
import io.sufeng.context.dto.app.FriendsApplyRecordDto;
import io.sufeng.context.dto.app.MessageUserSimpleInfoDto;
import io.sufeng.context.utils.JWTManager;
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
        return friendsService.getFriendsList(peopleId);
    }


}
