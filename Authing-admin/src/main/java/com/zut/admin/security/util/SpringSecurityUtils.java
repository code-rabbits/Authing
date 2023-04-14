package com.zut.admin.security.util;

import com.alibaba.fastjson.JSON;
import com.zut.admin.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Date 2023-04-14 10:44 星期五
 * @Author: 聂建强
 * @Description:  SpringSecurity相关的工具类的封装
 */
@Component
@Slf4j
public class SpringSecurityUtils {



    /**
     * 从springsecurity中获取用户信息
     *
     * @return
     */
    public static SysUser getUserInfo(){

        try {
            // 拿到当前的用户身份
            Object principalObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principalObj instanceof String){
                // 取出用户身份信息
                String principal=principalObj.toString();
                // 将JSON转成对象
                SysUser user= JSON.parseObject(principal,SysUser.class);
                return user;
            }

        }catch (Exception e){
            log.error("获取当前用户信息出错：{}",e.getMessage());
            e.printStackTrace();
        }

        return null;
    }






}
