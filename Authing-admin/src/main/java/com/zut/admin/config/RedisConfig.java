package com.zut.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.net.UnknownHostException;


@Configuration
public class RedisConfig {

    //编写我们自己的redisTemplate
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
        throws UnknownHostException{
        //我们为了自己开发方便，一般使用<String,Object>
        RedisTemplate<String,Object> template=new RedisTemplate<>();
        //配置具体的序列化方式

        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
