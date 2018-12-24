package com.yuxiao.wechat;


import com.yuxiao.wechat.entity.Poker;
import com.yuxiao.wechat.util.PokerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PokerTest {

    @Test
    public void testGetPoker(){
        PokerUtil.getPoker().stream().forEach(poker -> System.out.println(poker));
    }

    @Test
    public void testCutPokers(){
        List<Poker> pokers = PokerUtil.getPoker();
        List<List<Poker>> result = PokerUtil.cutPokers(pokers);
        result.stream().forEach(p -> {
            p.stream().forEach(p1 -> System.out.print(p1+","));
            System.out.println();
        });
    }
}
