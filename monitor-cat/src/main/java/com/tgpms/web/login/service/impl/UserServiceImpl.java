package com.tgpms.web.login.service.impl;

import com.tgpms.web.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author May
 * <p>
 * 用户相关操作实现接口
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
@Slf4j
public class UserServiceImpl implements UserService {

    /**
     * 根据用户 ID/CODE 查询用户是否存在
     *
     * @param userId 用户 ID
     * @param pwd 用户密码
     * @return null
     */
    @Override
    public boolean findByUserId(String userId, String pwd) {
        return "test".equals(userId) && "test".equals(pwd);
    }
}
