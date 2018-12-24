package com.yuxiao.wechat.entity;

import lombok.Data;

import java.util.List;

/**
 * 游戏用户
 */
@Data
public class Gamer {

    private String openid;
    private boolean isMaster;   //是否地主
    private List<Poker> pokers; //获得牌
    private List<Poker> remain; //剩余的牌
    private List<Poker> used;   //已打的牌

    private Integer seatNum;    //座位号
    private Integer nextSeatNum;    //下一个座位号

}
