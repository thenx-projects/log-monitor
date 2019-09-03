package com.tgpms.web.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

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
    @ApiOperation(value = "log-monitor 首页", notes = "log-monitor 首页", httpMethod = "POST")
    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("location", "C:\\Users\\May\\Downloads\\apache-tomcat-9.0.22-windows-x64\\apache-tomcat-9.0.22\\logs");
        String realPath2 = request.getServletContext().getRealPath(File.separator);
        return "index";
    }

    /**
     * log-monitor 首页根目录
     *
     * @return null
     */
    @ApiOperation(value = "log-monitor 首页 根目录", notes = "log-monitor 首页 根目录", httpMethod = "POST")
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String indexPath(HttpServletRequest request, Model model) {
        return this.index(request, model);
    }

    /**
     * 拿到项目全路径
     *
     * @param request 请求域
     * @return null
     */
    @ApiOperation(value = "拿到项目全路径", notes = "拿到项目全路径", httpMethod = "POST")
    @ResponseBody
    @PostMapping(value = "/serverPath")
    public String serverPath(HttpServletRequest request) {
        // 项目所在服务器全路径
        String realPath = request.getServletContext().getRealPath("/");

        // 请求地址
        String servletPath = request.getServletPath();

        // 服务器地址
        String serverName = request.getServerName();

        // 服务器端口
        Integer serverPort = request.getServerPort();

        // 项目名称
        String contextPath = request.getContextPath();

        // 请求全路径
        String requestPath = request.getRequestURL().toString();
        return realPath;
    }
}
