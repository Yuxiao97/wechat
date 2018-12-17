package com.yuxiao.wechat.base;


import lombok.Getter;
import lombok.Setter;

/**
 * 通用接口结果类
 */
@Getter
@Setter
public class ResultApi {

    // code默认为0表示成功，其他异常码根据具体业务场景自行定义
    private int code;
    private String msg = "";
    private Object data = "";

}
