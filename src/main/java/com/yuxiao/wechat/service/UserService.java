package com.yuxiao.wechat.service;


import com.yuxiao.wechat.entity.WechatUser;
import com.yuxiao.wechat.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService extends BaseService<WechatUser>{

    @Autowired
    private UserMapper userMapper;


    /**
     * 通过用户id获取微信用户
     * @param id
     * @return
     */
    public WechatUser getUserById(Long id){
        log.info("get user info by id:{}",id);
        WechatUser wxUser = new WechatUser();
        wxUser.setId(id);
        return userMapper.selectByPrimaryKey(wxUser);
    }


    /**
     * 通过openid获取微信用户
     * @param openid
     * @return
     */
    public WechatUser getUserByOpenid(String openid){
        WechatUser wxUser = new WechatUser();
        wxUser.setOpenId(openid);
        return userMapper.selectOne(wxUser);
    }

}
