package com.yuxiao.wechat.controller;


import com.yuxiao.wechat.entity.Gamer;
import com.yuxiao.wechat.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 玩家进入/退出房间Controller
 */
@RestController
@Slf4j
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;


    /**
     * 玩家进入房间
     * @param openid 玩家openid
     * @param nickName 玩家昵称
     * @return 当前玩家对象
     */
    @RequestMapping("/in")
    public Gamer getinRoom(@RequestParam String openid, @RequestParam String nickName){
        return roomService.getinRoom(openid, nickName);
    }


    /**
     * 玩家离开房价
     */
    @PostMapping("/out")
    public void getoutRoom(@RequestParam String roomNum, @RequestParam String openid){
        roomService.getoutRoom(roomNum, openid);
    }

}
