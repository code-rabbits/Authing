package com.zut.admin.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
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

    // // 令牌自定义标识
    // @Value("${jwt.header}")
    // private String header;
    //
    // // 令牌秘钥
    // @Value("${jwt.secret}")
    // private String secret;
    //
    // // 令牌有效期（默认30分钟）
    // @Value("${jwt.expireTime}")
    // private Long expireTime;

    @Resource
    private JwtProperties jwtProperties;

    private static final String LOGIN_USER_KEY = "login_user";


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

    // /**
    //  * 创建令牌
    //  *
    //  * @param userDetails 用户信息
    //  * @return 令牌
    //  */
    // public String createToken(UserDetails userDetails)
    // {
    //
    //     Map<String, Object> claims = new HashMap<>();
    //
    //     claims.put(LOGIN_USER_KEY, userDetails.getUsername());
    //
    //     return createToken(claims);
    // }


    /**
     * 创建令牌
     *
     * @param userDetails 用户信息
     * @return 令牌
     */
    public String createToken(String username)
    {

        Map<String, Object> claims = new HashMap<>();

        claims.put(LOGIN_USER_KEY, username);

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


        // String username = getUsernameFromToken(token);
        // return (username.equals(userDetails.getUsername()));

        return true;
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












}
