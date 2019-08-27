package com.tgpms.web.login.service;

/**
 * @author May
 * <p>
 * 用户操作相关接口
 */
public interface UserService {

    /**
     * 传入用户 ID 来判断是否存在
     *
     * @param userId 用户 ID
     * @param pwd 用户密码
     * @return null
     */
    boolean findByUserId(String userId, String pwd);
}
