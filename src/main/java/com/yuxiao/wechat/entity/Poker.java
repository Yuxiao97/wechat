package com.yuxiao.wechat.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 扑克牌实体类
 */
public class Poker implements Comparable<Poker> {

    @Getter
    @Setter
    private String color;   //花色
    @Getter
    @Setter
    private String number;  //数字

    private static Map<String, Integer> colorDic = new HashMap<>(6);
    private static Map<String, Integer> numberDic = new HashMap<>(14);
    @Getter
    private static List<Poker> pokers = new ArrayList<>(54);

    static {
        colorDic.put("♦", 1);
        colorDic.put("♣", 2);
        colorDic.put("❤", 3);
        colorDic.put("♠", 4);
        colorDic.put("小",5 );
        colorDic.put("大",6 );
        numberDic.put("3", 3);
        numberDic.put("4", 4);
        numberDic.put("5", 5);
        numberDic.put("6", 6);
        numberDic.put("7", 7);
        numberDic.put("8", 8);
        numberDic.put("9", 9);
        numberDic.put("10", 10);
        numberDic.put("J", 11);
        numberDic.put("Q", 12);
        numberDic.put("K", 13);
        numberDic.put("A", 14);
        numberDic.put("2", 15);
        numberDic.put("王", 16);

        String[] colors = { "♦", "♣", "❤", "♠" };
        String[] numbers = { "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q",
                "K", "A", "2" };
        for (String c : colors) {
            for (String n : numbers) {
                pokers.add(new Poker(c, n));
            }
        }
        pokers.add(new Poker("大", "王"));
        pokers.add(new Poker("小", "王"));
    }


    public Poker(String color, String number) {
        this.color = color;
        this.number = number;
    }


    @Override
    public String toString() {
        return this.color+this.number;
    }


    @Override
    public int compareTo(Poker o) {
        String color = o.getColor();
        String number = o.getNumber();
        if(numberDic.get(number) != numberDic.get(this.number)){
            return numberDic.get(number) - numberDic.get(this.number);
        }
        return colorDic.get(color) - colorDic.get(this.color);
    }



}
