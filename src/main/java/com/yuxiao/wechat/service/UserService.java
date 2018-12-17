package com.yuxiao.wechat.service;


import com.yuxiao.wechat.entity.WxUser;
import com.yuxiao.wechat.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public WxUser getUserById(Long id){
        log.info("get user info by id:{}",id);
        WxUser wxUser = new WxUser();
        wxUser.setId(id);
        return (WxUser) userMapper.selectByPrimaryKey(wxUser);
    }

}
