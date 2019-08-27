package com.tgpms.web.login.dto;

import lombok.Data;

/**
 * @author May
 * <p>
 * 用户实体
 */
@Data
public class Users {

    /**
     * 用户编码ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPwd;
}
