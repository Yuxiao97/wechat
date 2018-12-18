package com.yuxiao.wechat.controller;


import com.alibaba.fastjson.JSONObject;
import com.yuxiao.wechat.base.ResultApi;
import com.yuxiao.wechat.entity.WechatUser;
import com.yuxiao.wechat.service.UserService;
import com.yuxiao.wechat.util.HttpUtil;
import com.yuxiao.wechat.util.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信交互基本接口类
 */
@RestController
@Slf4j
public class WechatController {

    // code换取openid和session_key的api
    private static final String CODE_2SESSION_API = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
    @Value("${wechat.appid}")
    private static String APP_ID;
    @Value("wechat.appSecret")
    private static String APP_SECRET;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private UserService userService;


    /*@RequestMapping("/testEhcache")
    public String testEhcache(String code){
        Cache cache = cacheManager.getCache("wxSessionKey");
        if(cache.get("111") != null){
            String sessionKey = (String) cache.get("111").get();
            log.info("get session_key from ehcache :{}",sessionKey);
        } else {
            cache.put("111",code);
            log.info("save session_key to ehcache:{}",code);
        }
        return "success";
    }*/


    /**
     * code换session_key
     * @return
     */
    @RequestMapping("/code2session")
    public ResultApi code2Session(String code){
        ResultApi result = new ResultApi();
        log.info("start code 2 session.");
        String url = String.format(CODE_2SESSION_API, APP_ID, APP_SECRET, code);
        try{
            JSONObject sessionObj = JSONObject.parseObject(HttpUtil.doGet(url));
            Cache cache = cacheManager.getCache("wxSessionKey");
            cache.put(sessionObj.getString("openid"), sessionObj.get("session_key"));
            result.setData(sessionObj.getString("openid"));
            log.info("get session_key successful.");
        } catch (Exception e){
            result.setCode(1);
            result.setMsg(e.getMessage());
            return result;
        }
        return result;
    }


    @RequestMapping("/login")
    public ResultApi login(HttpServletRequest request){
        ResultApi result = new ResultApi();
        log.info("---->start check user data.");
        String requestData = request.getParameter("data");
        // check param
        if(StringUtils.isEmpty(requestData)){
            result.setCode(1);
            result.setMsg("无效的data参数值.");
            return result;
        }
        String openKey = request.getParameter("openKey");
        if(StringUtils.isEmpty(openKey)){
            result.setCode(1);
            result.setMsg("无效的openKey参数值.");
            return result;
        }
        JSONObject requestJson = JSONObject.parseObject(requestData);
        String encryptedData = requestJson.getString("encryptedData");
        String iv = requestJson.getString("iv");
        try{
            JSONObject userInfo = WechatUtil.getUserInfo(encryptedData,
                    cacheManager.getCache("wxSessionKey").get(openKey).toString(), iv);
            String openId = userInfo.getString("openId");
            WechatUser dbUser = new WechatUser();
            dbUser.setOpenId(openId);
            dbUser = userService.selectOne(dbUser);
            if(dbUser != null){
                Long userId = dbUser.getId();
                dbUser = setPropertiesAndGet(userInfo);
                dbUser.setId(userId);
                userService.updateByPrimaryKeySelective(dbUser);
                log.info("update user info, openId:{}", openId);
            } else {
                // 新增用户
                userService.insertSelective(setPropertiesAndGet(userInfo));
                log.info("add one user, openId:{}", openId);
            }
        } catch (Exception e){
            result.setCode(1);
            result.setMsg(e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 设置用户属性并返回用户对象
     * @return
     */
    private WechatUser setPropertiesAndGet(JSONObject jsonUser){
        String openId = jsonUser.getString("openId");
        String nickName = jsonUser.getString("nickName");
        Integer gender = jsonUser.getInteger("gender");
        String city = jsonUser.getString("city");
        String province = jsonUser.getString("province");
        String country = jsonUser.getString("country");
        String avatarUrl = jsonUser.getString("avatarUrl");
        String unionId = jsonUser.getString("unionId");

        WechatUser dbUser = new WechatUser();
        dbUser.setOpenId(openId);
        dbUser.setNickName(nickName);
        dbUser.setAvatarUrl(avatarUrl);
        dbUser.setCity(city);
        dbUser.setProvince(province);
        dbUser.setCountry(country);
        dbUser.setGender(gender);
        dbUser.setUnionId(unionId);
        return dbUser;
    }

}
