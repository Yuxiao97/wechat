package com.yuxiao.wechat.entity;

import lombok.Data;

import java.util.List;

/**
 * 游戏用户
 */
@Data
public class Gamer {

    private String openid;
    private String nickName;    //用户昵称
    private String roomNum;     //房间号
    private boolean isMaster;   //是否地主
    private List<Poker> pokers; //获得牌
    private List<Poker> remain; //剩余的牌
    private List<Poker> used;   //已打的牌

    private Integer seatNum;    //座位号
    private Integer nextSeatNum;    //下一个座位号


    public Gamer(String openid, String nickName){
        this.openid = openid;
        this.nickName = nickName;
    }



    /**
     * 设置用户的初始牌时，手中剩余的牌等于发到手的牌
     * @param pokers
     */
    public void setPokers(List<Poker> pokers){
        this.pokers = pokers;
        this.remain = pokers;
    }

}
