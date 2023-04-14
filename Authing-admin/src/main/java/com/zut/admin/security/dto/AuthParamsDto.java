package com.zut.admin.security.dto;

import lombok.Data;

/**
 * @Date 2023-04-14 11:39 星期五
 * @Author: 聂建强
 * @Description:  认证用户统一请求参数
 */

@Data
public class AuthParamsDto {

    private String username;

    private String password;

    private String app_code;


}
