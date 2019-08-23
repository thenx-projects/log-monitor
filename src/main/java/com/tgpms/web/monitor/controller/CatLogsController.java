package com.tgpms.web.monitor.controller;

import com.tgpms.common.Result;
import com.tgpms.exception.ExceptionExplain;
import com.tgpms.exception.QueryException;
import com.tgpms.web.monitor.service.CatLogsService;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author May
 * <p>
 * 日志查询相关接口
 */
@Api(value = "CatLogsController", tags = "日志查询相关接口")
@RestController
@RequestMapping(value = "/monitor/CatLogsController")
@Slf4j
public class CatLogsController {

    @Resource
    private CatLogsService catLogsService;

    /**
     * 当前目录所有日志
     *
     * @param location 目录地址
     * @return null
     */
    @ApiOperation(value = "当前目录所有日志", notes = "当前目录所有日志", httpMethod = "POST")
    @PostMapping(value = "/findAll")
    public Result findAll(@RequestParam("location") String location) {
        Result result = new Result();
        List<String> allFiles = new ArrayList<>();
        if (StringUtil.isNullOrEmpty(location)) {
            result.setMsg(ExceptionExplain.EMPTY_BY_DATA.getExplain());
            result.setSuccess(false);
            throw new QueryException(ExceptionExplain.EMPTY_BY_DATA.getExplain());
        } else {
            try {
                allFiles = catLogsService.findAll(location);
            } catch (Exception e) {
                result.setMsg(ExceptionExplain.ERROR_BY_QUERY.getExplain());
                result.setSuccess(false);
                e.printStackTrace();
                throw new QueryException(ExceptionExplain.ERROR_BY_QUERY.getExplain());
            }
            result.setData(allFiles);
        }
        return result;
    }
}
