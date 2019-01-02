package com.yuxiao.wechat.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 房间
 */

@Data
public class Room {

    private String roomNum; //房间号，唯一
    private int currentGamerNum = 0;    //房间的当前用户数量
    private List<Gamer> gamers = new ArrayList<>(3);   //进入房间的用户
    private List<Poker> pokers;   //原始洗好的牌
    private List<Poker> bottomPoker;    //底牌

    public Room(){
        this.roomNum = System.currentTimeMillis()+"";
    }

    /**
     * 添加用户，并设置当前房间人数
     * @return
     */
    public boolean addGamer(Gamer gamer){
        // 房间最多三人
        if(gamers.size() == 3){
            return false;
        }
        gamers.add(gamer);
        currentGamerNum = gamers.size();
        return true;
    }


    /**
     * 玩家离房间
     * @param openid 玩家openid
     * @return
     */
    public boolean removeGamer(String openid){
        this.gamers = gamers.stream().filter(gamer -> !gamer.getOpenid().equals(openid))
                .collect(Collectors.toList());
        this.currentGamerNum = this.gamers.size();
        return true;
    }

}
