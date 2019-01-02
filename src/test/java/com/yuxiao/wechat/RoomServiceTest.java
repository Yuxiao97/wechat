package com.yuxiao.wechat;

import com.yuxiao.wechat.entity.Gamer;
import com.yuxiao.wechat.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Test
    public void testGamerInRoom(){
        Gamer gamer = roomService.getinRoom("yuxiao97", "雨潇先生");
        log.info("new gamer:{}", gamer);
        roomService.getoutRoom(gamer.getRoomNum(),gamer.getOpenid());
    }

}
