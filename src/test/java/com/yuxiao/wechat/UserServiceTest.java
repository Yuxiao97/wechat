package com.yuxiao.wechat;


import com.yuxiao.wechat.entity.WechatUser;
import com.yuxiao.wechat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testAddWechatUser(){
        WechatUser wechatUser = new WechatUser();
        wechatUser.setOpenId("gsdgasdgdga");
        wechatUser.setNickName("哇哈哈");
        userService.insertSelective(wechatUser);
    }

    @Test
    public void getUserById(){
        WechatUser user = new WechatUser();
        user.setId(1L);
        log.info("{}",userService.selectByPrimaryKey(user));
    }


    @Test
    public void getUserByOpenid(){
        String openid = "yuxiao0907";
        WechatUser user = userService.getUserByOpenid(openid);
        if(user == null){
            log.info("不存在微信号为{}的用户", openid);
        } else {
            log.info("{}", user);
        }
    }
}
