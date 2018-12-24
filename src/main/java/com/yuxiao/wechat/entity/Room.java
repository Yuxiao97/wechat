package com.yuxiao.wechat.entity;

import lombok.Data;

import java.util.List;


/**
 * 房间
 */

@Data
public class Room {

    private String roomNum; //房间号，唯一
    private List<Gamer> gamers;   //进入房间的用户

}
