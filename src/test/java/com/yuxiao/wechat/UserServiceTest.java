package com.yuxiao.wechat;


import com.yuxiao.wechat.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private DataSource dataSource;


    @Autowired
    private UserService userService;

    @Test
    public void getUserById(){
        System.out.println(dataSource);
        System.out.println(userService.getUserById(Long.parseLong("1")));
    }
}
