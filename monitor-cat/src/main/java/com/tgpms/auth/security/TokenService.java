package com.tgpms.auth.security;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class TokenService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${token.header}")
    private String tokenHeader;

    @Resource
    private Redisson redisson;

    /**
     * 校验Token
     *
     * @param token token
     * @return null
     */
    public boolean checkToken(String token) {
        Map<String, Object> stringObjectMap = JavaWebToken.parserJavaWebToken(token);
        String toeknRe = null;
        if (null == stringObjectMap) {
            return false;
        }

        try {
            toeknRe = String.valueOf(redisTemplate.opsForValue().get(stringObjectMap.get("username")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return StringUtils.isNotBlank(toeknRe) && token.equals(toeknRe);
    }

    /**
     * 登录设置 Token
     *
     * @param username 根据 username 生成Tokne
     * @return null
     */
    public String login(String username) {
        String token = "";
        RLock lock = redisson.getLock(UUID.randomUUID().toString().replace("-", ""));

        log.info("登录成功" + username);
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("rightTime", Calendar.getInstance().getTime());
        token = JavaWebToken.createJavaWebToken(map);
        redisTemplate.delete(username);
        log.info("\n Token -> " + token);

        // 拿到 Redis连接池
        try {
            lock.tryLock(5, TimeUnit.SECONDS);
            redisTemplate.opsForValue().setIfAbsent(username, token, 1, TimeUnit.HOURS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return token;
    }

    /**
     * 获取登录用户信息
     *
     * @return null
     */
    public static String getLoginUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        } else {
            return null;
        }
    }

    /**
     * 从token中获取用户名
     *
     * @return
     */
    public String getUserName(HttpServletRequest request) {
        String authToken = request.getHeader(this.tokenHeader);
        String username = Objects.requireNonNull(JavaWebToken.parserJavaWebToken(authToken)).get("username").toString();
        username = username.trim();
        return username;
    }
}
