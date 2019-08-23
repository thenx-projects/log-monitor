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
            }
            result.setData(allFiles);
        }
        return result;
    }

    /**
     * 查询 catalina 日志
     *
     * @param location 文件路径
     * @param fileName 文件名称
     * @return null
     */
    @ApiOperation(value = "查询 catalina 日志", notes = "查询 catalina 日志", httpMethod = "POST")
    @PostMapping(value = "/catCatalina")
    public Result catCatalina(@RequestParam("location") String location, @RequestParam("fileName") String fileName) {
        Result result = new Result();
        List<String> catalina = new ArrayList<>();
        if (StringUtil.isNullOrEmpty(location) || StringUtil.isNullOrEmpty(fileName)) {
            result.setSuccess(false);
            result.setMsg(ExceptionExplain.EMPTY_BY_LOCATION_OR_FILENAME.getExplain());
            throw new QueryException(ExceptionExplain.EMPTY_BY_LOCATION_OR_FILENAME.getExplain());
        } else {
            try {
                catalina = catLogsService.catCatalina(location, fileName);
            } catch (Exception e) {
                result.setMsg(ExceptionExplain.ERROR_BY_QUERY.getExplain());
                result.setSuccess(false);
                e.printStackTrace();
            }
            result.setData(catalina);
            return result;
        }
    }


}
