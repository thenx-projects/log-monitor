package com.tgpms.web.index;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author may
 *
 * log-monitor 首页相关后台接口
 */
@Api(value = "IndexController", tags = "log-monitor 首页相关后台接口")
@Controller
@RequestMapping(value = "/")
@Slf4j
public class IndexController {

    /**
     * log-monitor 首页
     *
     * @return null
     */
    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        log.info("\n ----------> into the index method and page");
        return "index";
    }
}
