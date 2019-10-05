







package org.thenx.web.index;

import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "IndexController", tags = "log-monitor 首页相关后台接口")
@Controller
@RequestMapping(value = "/")
@Slf4j
public class IndexController {

    private static String DEFAULT_LOCATION = "C:\\Users";

    private static String TEST_LOCATION = "C:\\Users\\May\\Downloads\\apache-tomcat-9.0.22-windows-x64\\apache-tomcat-9.0.22\\logs";

    private final static String JS_APP_LOCATION = "E:\\apache-tomcat-app\\logs";

    private final static String SM_LOCATION = "E:\\apache-tomcat-sm\\logs";

    private final static String REIMBSTEST = "E:\\apache-tomcat-8.0.38-REIMBSTEST\\logs";

    private final static String JS_COP = "E:\\apache-tomcat-8.0.38-JSCOPTEST\\logs";

    @ApiOperation(value = "log-monitor 首页", notes = "log-monitor 首页", httpMethod = "POST")
    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("location", DEFAULT_LOCATION);
        model.addAttribute("test_location", TEST_LOCATION);
        model.addAttribute("js_app_location", JS_APP_LOCATION);
        model.addAttribute("sm_location", SM_LOCATION);
        model.addAttribute("reimbstest", REIMBSTEST);
        model.addAttribute("js_cop", JS_COP);
        return "index";
    }

    @ApiOperation(value = "log-monitor 保证后台同步默认值", notes = "log-monitor 保证后台同步默认值", httpMethod = "POST")
    @RequestMapping(value = "/enterSm", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public boolean enterSm(String newLocation) {
        if (!StringUtil.isNullOrEmpty(newLocation)) {
            DEFAULT_LOCATION = newLocation;
            return true;
        } else {
            DEFAULT_LOCATION = "C:\\Users\\";
            return false;
        }
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
