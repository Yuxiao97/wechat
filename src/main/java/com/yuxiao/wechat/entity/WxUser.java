package com.yuxiao.wechat.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WxUser {

    private Long id;
    private String openId;
    private String nickName;
    private int gender;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionId;
    private Date createTime;

}
