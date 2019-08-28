package com.tgpms.web.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author May
 * <p>
 * 集成 Thymeleaf 测试
 */
@Controller
@RequestMapping("/test")
public class IndexController {

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}
