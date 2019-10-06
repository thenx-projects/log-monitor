package org.thenx.dto;

import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thenx.common.exception.ExceptionExplain;
import org.thenx.common.exception.QueryException;
import org.thenx.common.vo.Result;
import org.thenx.windows.service.WindowsTomcatControlService;

import javax.annotation.Resource;

/**
 * @author May
 * <p>
 * Tomcat 控制接口方法实现
 */
@RestController
@RequestMapping(value = "/tomcat")
@Slf4j
@Api(value = "TomcatController", tags = "Tomcat 控制接口方法实现")
public class TomcatController {

    @Resource
    private WindowsTomcatControlService windowsTomcatControlService;

    /**
     * 重启Tomcat
     *
     * @param location 路径
     * @return x
     */
    @ApiOperation(value = "重启Tomcat", notes = "重启Tomcat", httpMethod = "POST")
    @PostMapping(value = "/rebootTomcatControl")
    public Result rebootTomatControl(@RequestParam("location") String location) {
        Result result = new Result();
        if (StringUtil.isNullOrEmpty(location)) {
            throw new QueryException(ExceptionExplain.ERROR_BY_QUERY.getExplain());
        } else {
            boolean b = windowsTomcatControlService.rebootTomcat(location);
            result.setData(b);
        }
        return result;
    }
}
