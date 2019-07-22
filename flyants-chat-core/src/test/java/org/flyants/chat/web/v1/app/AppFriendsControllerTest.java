package org.flyants.chat.web.v1.app;

import org.flyants.chat.domain.service.FriendsService;
import org.flyants.chat.dto.app.FriendsApplyRecordDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppFriendsControllerTest {

    @Autowired
    private FriendsService friendsService;

    @Test
    public void applyFriends() {

    }

    @Test
    public void handlerFriendsApply() {

    }

    @Test
    public void getFriendsApplyList() {
        List<FriendsApplyRecordDto> friendsApplyList = friendsService.getFriendsApplyList("4f415473-7e32-4ebb-99e2-f70b3fd0b784");
        System.out.println(friendsApplyList.size());
    }

    @Test
    public void getFriendsList() {
    }
}