package com.tgpms.web.monitor.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tgpms.common.PageView;
import com.tgpms.common.Result;
import com.tgpms.exception.ExceptionExplain;
import com.tgpms.exception.QueryException;
import com.tgpms.web.monitor.dto.LogsDto;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<LogsDto> allFiles = new ArrayList<>();
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
     * 查询指定日志
     *
     * @param location 文件路径
     * @param fileName 文件名称
     * @return null
     */
    @ApiOperation(value = "查询指定日志", notes = "查询指定日志", httpMethod = "POST")
    @PostMapping(value = "/catLogs")
    public Result catLogs(@RequestParam("location") String location, @RequestParam("fileName") String fileName) {
        Result result = new Result();
        List<String> catalina = new ArrayList<>();
        if (StringUtil.isNullOrEmpty(location) || StringUtil.isNullOrEmpty(fileName)) {
            result.setSuccess(false);
            result.setMsg(ExceptionExplain.EMPTY_BY_LOCATION_OR_FILENAME.getExplain());
            throw new QueryException(ExceptionExplain.EMPTY_BY_LOCATION_OR_FILENAME.getExplain());
        } else {
            try {
                catalina = catLogsService.catLogs(location, fileName);
            } catch (Exception e) {
                result.setMsg(ExceptionExplain.ERROR_BY_QUERY.getExplain());
                result.setSuccess(false);
                e.printStackTrace();
            }
            result.setData(catalina);
            return result;
        }
    }

    /**
     * 查询指定日志 分页版
     *
     * @param location 地址
     * @param logsName 日志名
     * @param pageNo   分页码农
     * @return null
     */
    @ApiOperation(value = "查询指定日志 分页版", notes = "查询指定日志 分页版", httpMethod = "POST")
    @PostMapping(value = "/catLogsPage")
    public PageView catLogsPage(@RequestParam("location") String location, @RequestParam("fileName") String logsName,
                                @RequestParam("pageNo") Integer pageNo) {
        PageView pageView = new PageView();
        pageView.setPageNow(pageNo);
        Map<String, String> map = new HashMap<>(16);
        map.put("location", location);
        map.put("logsName", logsName);
        pageView.setQueryMap(map);
        try {
            pageView = catLogsService.catLogsPage(pageView);
        } catch (Exception e) {
            e.printStackTrace();
            throw new QueryException(e.getMessage() + ExceptionExplain.ERROR_BY_PARSING.getExplain());
        }
        return pageView;
    }

    /**
     * 查询所有日志 分页版
     *
     * @param location 路径
     * @param pageNo   页码
     * @return null
     */
    @ApiOperation(value = "查询所有日志 分页版", notes = "查询所有日志 分页版", httpMethod = "POST")
    @PostMapping(value = "/findAllPage")
    public PageView findAllPage(@RequestParam("location") String location, @RequestParam("pageNo") Integer pageNo) {
        PageView pageView = new PageView();
        pageView.setPageNow(pageNo);
        Map<String, String> map = new HashMap<>(16);
        map.put("location", location);
        pageView.setQueryMap(map);
        try {
            pageView = catLogsService.findAllPage(pageView);
        } catch (Exception e) {
            e.printStackTrace();
            throw new QueryException(e.getMessage() + ExceptionExplain.ERROR_BY_PARSING.getExplain());
        }
        return pageView;
    }

    /**
     * 当前目录所有日志 仅仅查询名称
     *
     * @param location 路径
     * @return null
     */
    @ApiOperation(value = "当前目录所有日志 仅仅查询名称", notes = "当前目录所有日志 仅仅查询名称", httpMethod = "POST")
    @PostMapping(value = "/findAllName")
    public Result findAllName(@RequestParam("location") String location) {
        Result result = new Result();
        List<String> allName = new ArrayList<>();
        try {
            allName = catLogsService.findAllName(location);
        } catch (Exception e) {
            result.setMsg(ExceptionExplain.ERROR_BY_PARSING.getExplain());
            result.setSuccess(false);
            e.printStackTrace();
        }
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(allName));
        result.setData(jsonArray);
        return result;
    }

    /**
     * 根据日志名称查询相关日志
     *
     * @param location 路径
     * @param logsName 日志名称
     * @return null
     */
    @ApiOperation(value = "根据日志名称查询相关日志", notes = "根据日志名称查询相关日志", httpMethod = "POST")
    @PostMapping(value = "/logsName")
    public Result logsName(@RequestParam("location") String location, @RequestParam("logsName") String logsName) {
        Result result = new Result();
        List<String> byLogsName;
        try {
            byLogsName = catLogsService.findByLogsName(location, logsName);
        } catch (Exception e) {
            result.setMsg(ExceptionExplain.ERROR_BY_PARSING.getExplain());
            result.setSuccess(false);
            e.printStackTrace();
            throw new QueryException(ExceptionExplain.ERROR_BY_PARSING.getExplain());
        }
        result.setData(byLogsName);
        return result;
    }

    /**
     * 根据日志名称精确查找
     *
     * @param location 路径
     * @param logsName 日志名称
     * @return null
     */
    @ApiOperation(value = "根据日志名称精确查找", notes = "根据日志名称精确查找", httpMethod = "POST")
    @PostMapping(value = "/findByEqualsName")
    public Result findByEqualsName(@RequestParam("location") String location, @RequestParam("logsName") String logsName) {
        Result result = new Result();
        List<String> byLogsName;
        try {
            byLogsName = catLogsService.findByEqualsName(location, logsName);
        } catch (Exception e) {
            result.setMsg(ExceptionExplain.ERROR_BY_PARSING.getExplain());
            result.setSuccess(false);
            e.printStackTrace();
            throw new QueryException(ExceptionExplain.ERROR_BY_PARSING.getExplain());
        }
        result.setData(byLogsName);
        return result;
    }

}
