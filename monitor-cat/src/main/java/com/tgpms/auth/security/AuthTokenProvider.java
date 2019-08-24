package com.tgpms.auth.security;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * jeff by 2019-4-4
 * 用于进行token的验证
 */
@Component
@Slf4j
public class AuthTokenProvider implements AuthenticationProvider {

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            return new UsernamePasswordAuthenticationToken(auth.getPrincipal(), null, new ArrayList<>());
        }
        String token = (String) authentication.getPrincipal();
        if (token != null) {
            if (!tokenService.checkToken(token)) {
                throw new CredentialsExpiredException("令牌已失效,请重新登录！");
            }
        } else {
            throw new BadCredentialsException("Invalid token String.");
        }
        log.debug("验证通过.");
        return new UsernamePasswordAuthenticationToken(token, null, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
