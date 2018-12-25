package com.yuxiao.wechat.service;


import com.yuxiao.wechat.entity.Gamer;
import com.yuxiao.wechat.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;


/**
 * 房间服务类
 */
@Service
public class RoomService {

    @Autowired
    private CacheManager cacheManager;

    private static String lastRoomNum;  //最后一个可用的房间号

    /**
     * 玩家进入房间(顺序排队进入，后续考虑如何并发)
     * @param openid 玩家openid
     * @param nickName 玩家昵称
     * @return
     */
    public synchronized Gamer getinRoom(String openid, String nickName){
        Gamer gamer = new Gamer(openid, nickName);
        Cache cache = cacheManager.getCache("room");
        Room room = cache.get(lastRoomNum, Room.class);
        if(room == null || room.getCurrentGamerNum() == 3){
            // 新建房间
            room = new Room();
            lastRoomNum = room.getRoomNum();
        }
        gamer.setRoomNum(lastRoomNum);
        gamer.setSeatNum(room.getCurrentGamerNum()+1);
        room.addGamer(gamer);
        return gamer;
    }

    /**
     * 玩家离开房间
     * @param roomNum 房间号
     */
    public void getoutRoom(String roomNum, String openid){
        Cache cache = cacheManager.getCache("room");
        Room room = cache.get(roomNum, Room.class);
        room.removeGamer(openid);
        if (room.getCurrentGamerNum() == 0){
            // 房间没有玩家时，解散该房间
            cache.evict(roomNum);
        }
    }

}
