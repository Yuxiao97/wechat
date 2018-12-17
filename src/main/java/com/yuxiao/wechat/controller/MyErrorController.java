package com.yuxiao.wechat.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class MyErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        throw new RuntimeException("迷路了？请记得归途路！");
    }
}
