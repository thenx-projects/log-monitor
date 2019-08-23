package com.tgpms.auth.security;

import com.tgpms.auth.config.TokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * jeff by 2019-4-4
 * 用户JWT的基础类
 */
public class JavaWebToken {

    private static Logger log = LoggerFactory.getLogger(JavaWebToken.class);

    public static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private static TokenProperties tokenp;

    /**
     * 该方法使用HS256算法和Secret:bankgl生成signKey
     *
     * @return null
     */
    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("token");
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    /**
     * 使用HS256签名算法和生成的signingKey最终的Token,claims中是有效载荷
     * <p>
     * setIssuer("Jersey-Security-Basic") 设置发行人
     * setSubject("subject") 设置抽象主题
     * setAudience("login") 设置角色
     * setExpiration(getDate()) 过期时间
     * setIssuedAt(new Date()) 设置现在时间
     * setClaims(claims) 给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
     * signWith( SignatureAlgorithm.HS256,getKeyInstance()) 设置签名使用的签名算法和签名使用的秘钥
     * compact() 生成 Token
     *
     * @param claims
     * @return null
     */
    public static String createJavaWebToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setIssuer("Jersey-Security-Basic")
                .setSubject("subject")
                .setAudience("login")
                .setExpiration(getDate())
                .setIssuedAt(new Date())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, getKeyInstance())
                .compact();
    }

    /**
     * 解析Token，同时也能验证Token，当验证失败返回null
     */
    public static Map<String, Object> parserJavaWebToken(String jwt) {
        try {
            return Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            log.error("解析失败");
            return null;
        }
    }

    /**
     * 过期时间
     *
     * @param jwt
     * @return null
     */
    public static Long rightNowTime(String jwt) {
        return Jwts.parser()
                .setSigningKey(getKeyInstance())
                .parseClaimsJws(jwt)
                .getBody().getExpiration().getTime() - System.currentTimeMillis();
    }

    public static Claims rightTime(String jwt) {
        return Jwts.parser()
                .setSigningKey(getKeyInstance())
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static Date getDate() {
        try {
            long currentTime = System.currentTimeMillis();
            currentTime += 30 * 60 * 1000 * 2;
            return new Date(currentTime);
        } catch (Exception e) {
            e.printStackTrace();
            long currentTime = System.currentTimeMillis();
            currentTime += 30 * 60 * 1000 * 2;
            return new Date(currentTime);
        }
    }

}