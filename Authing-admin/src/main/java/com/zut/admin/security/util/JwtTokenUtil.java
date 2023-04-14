package com.zut.admin.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2023-04-14 10:44 星期五
 * @Author: 聂建强
 * @Description:  JWT生成token的工具类
 */

@Component
public class JwtTokenUtil {

    @Resource
    private JwtProperties jwtProperties;

    private static final String LOGIN_USER_KEY = "login_user";
    private static final String CLAIM_KEY_CREATED = "created";


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims)
    {
        // 过期时间
        Date expirationDate = new Date(System.currentTimeMillis() + jwtProperties.getExpireTime());

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate) //设置过期时间
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret()).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 创建令牌
     *
     * @param username 用户信息
     * @return 令牌
     */
    public String createToken(String username)
    {

        Map<String, Object> claims = new HashMap<>();

        claims.put("sub", username);
        claims.put(CLAIM_KEY_CREATED, new Date());

        return createToken(claims);
    }


    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param userDetails 用户
     * @return 是否有效
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }



    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }


    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = createToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

}
