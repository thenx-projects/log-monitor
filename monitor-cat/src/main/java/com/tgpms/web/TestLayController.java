package com.tgpms.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author may
 *
 * 测试 LayUI 和 Thymeleaf 的集成
 */
@Controller
@RequestMapping(value = "/index")
public class TestLayController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "index";
    }
}
