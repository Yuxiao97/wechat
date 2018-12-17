package com.yuxiao.wechat.mapper;

import com.yuxiao.wechat.entity.WxUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

@Repository
public interface UserMapper extends BaseMapper<WxUser> {
}
