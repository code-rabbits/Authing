package com.zut.admin.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Date 2023-04-14 13:23 星期五
 * @Author: 聂建强
 * @Description:   BCryptPassword 工具类
 */

@Component
public class PasswordEncoderUtil {

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 生成密码
     * @param password
     * @return
     */
    public String PasswordEncoder(String password){

        return bCryptPasswordEncoder.encode(password);
    }

    /**
     * 验证密码
     * @param rawPassword  明文
     * @param encodedPassword  加密后的字符串
     * @return
     */
    public boolean PasswordMatches(String rawPassword, String encodedPassword){

        return bCryptPasswordEncoder.matches(rawPassword,encodedPassword);
    }


}
