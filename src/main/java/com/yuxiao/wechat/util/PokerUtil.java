package com.yuxiao.wechat.util;


import com.yuxiao.wechat.entity.Poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PokerUtil {

    /**
     * 获取一副洗好的扑克牌
     * @return
     */
    public static List<Poker> getPoker(){
        List<Poker> tmpPokers = Poker.getPokers().stream().collect(Collectors.toList());
        Collections.shuffle(tmpPokers);
        return tmpPokers;
    }


    /**
     * 发牌，最后一元素为底牌
     * @param pokers
     * @return
     */
    public static List<List<Poker>> cutPokers(List<Poker> pokers){
        List<Poker> p1 = new ArrayList<>(17);
        List<Poker> p2 = new ArrayList<>(17);
        List<Poker> p3 = new ArrayList<>(17);
        List<Poker> bottomPoker = new ArrayList<>(3);

        Iterator<Poker> iterator = pokers.iterator();
        int i = 0;
        while (iterator.hasNext() && i<17){
            p1.add(iterator.next());
            iterator.remove();
            p2.add(iterator.next());
            iterator.remove();
            p3.add(iterator.next());
            iterator.remove();
            i++;
        }
        bottomPoker.addAll(pokers);
        List<List<Poker>> result = new ArrayList<>();
        result.add(p1);
        result.add(p2);
        result.add(p3);
        result.add(bottomPoker);
        return result;
    }

}
