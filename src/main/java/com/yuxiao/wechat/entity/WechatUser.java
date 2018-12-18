package com.yuxiao.wechat.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Table(name = "user")
public class WechatUser implements Serializable {

    @Id
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
