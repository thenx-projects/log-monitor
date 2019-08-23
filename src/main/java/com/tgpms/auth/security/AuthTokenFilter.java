package com.tgpms.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgpms.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token拦截器
 * by jeff 2019-4-4
 */
public class AuthTokenFilter extends AbstractAuthenticationProcessingFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${token.header}")
    private String tokenHeader;

    @Value("${token.name}")
    private String tokenName;

    public AuthTokenFilter(RequestMatcher matcher) {
        super(matcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String authToken = request.getHeader(this.tokenHeader);
        if (StringUtils.isEmpty(authToken)) {
            authToken = request.getParameter(this.tokenName);
        }
        if (authToken == null) {
            throw new AuthenticationCredentialsNotFoundException("无访问权限！");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authToken, null);
        return getAuthenticationManager().authenticate(authentication);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException authException) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String message;
        if (authException.getCause() != null) {
            message = authException.getCause().getMessage();
        } else {
            message = authException.getMessage();
        }

        Result baseResponse = new Result();

        baseResponse.setSuccess(true);
        baseResponse.setMsg(message);

        byte[] body = new ObjectMapper().writeValueAsBytes(baseResponse);
        response.getOutputStream().write(body);
    }
}
