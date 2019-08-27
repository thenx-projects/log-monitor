package com.tgpms.web.login.controller;

import com.tgpms.common.Result;
import com.tgpms.exception.ExceptionExplain;
import com.tgpms.exception.QueryException;
import com.tgpms.web.login.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author May
 * <p>
 * 用户相关操作接口
 */
@RestController
@RequestMapping(value = "/login")
@Api(value = "CatLogsController", tags = "日志查询相关接口")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录校验相关接口
     *
     * @param userId 用户 ID
     * @param userPwd 用户密码
     * @return null
     */
    @ApiOperation(value = "用户登录校验相关接口", notes = "用户登录校验相关接口", httpMethod = "POST")
    @PostMapping(value = "/userLogin")
    public Result userLogin(@RequestParam("userId") String userId, @RequestParam("userPwd") String userPwd) {
        Result result = new Result();
        boolean byUserId = userService.findByUserId(userId, userPwd);
        if (byUserId) {
            result.setData(true);
        } else {
            result.setMsg(ExceptionExplain.ERROR_BY_PARSING.getExplain());
            result.setSuccess(false);
            throw new QueryException(ExceptionExplain.ERROR_BY_PARSING.getExplain());
        }
        return result;
    }
}
