package com.zut.admin.security.util;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Date 2023-04-14 13:16 星期五
 * @Author: 聂建强
 * @Description:
 */

@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "jwt") //与配置文件中的数据关联起来(这个注解会自动匹配jwt开头的配置)
public class JwtProperties {

    // token 的 header
    private String header;

    // 令牌秘钥
    private String secret;

    // 令牌有效期
    private Long expireTime;
}
