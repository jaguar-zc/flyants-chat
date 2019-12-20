package io.sufeng.web.v1.app;

import io.sufeng.context.AppConfiguration;
import io.sufeng.context.domain.service.FriendsService;
import io.sufeng.context.dto.app.FriendsApplyRecordDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfiguration.class)
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