package com.mugbya;

import com.mugbya.entities.User;
import com.mugbya.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {

    Byte ENABLE = 1;
    Byte DISABLE = 0;

    @Autowired
    UserService userService;

    @Test
    public void testAddUser() throws Exception{
        List<User> userList = new ArrayList();
        for (int i=0; i< 10; i++) {
            User user = new User();
            user.setId("100000000"+i);
            user.setUsername("jack-"+i);
            user.setAge(30+i);
            user.setGender("男");
            user.setDesc("a man");
            user.setStatue(ENABLE);
            user.setCreateTime(new Date());
            userList.add(user);
        }
        userService.batchSave(userList);
    }

    @Test
    public void testFindUser() throws Exception{
        String username = "jack";
        List<User> userList = userService.getUserByUsername(username);
        System.out.println(userList);
    }

    @Test
    public void testFindUserByFuzzy() throws Exception{
        String username = "jack";
        List<SearchHit<User>>  userList = userService.getUserByFuzzy(username);
        System.out.println(userList);
    }

}
