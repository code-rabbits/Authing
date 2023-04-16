package com.zut.admin.controller;

import com.zut.admin.entity.SysUser;
import com.zut.admin.service.AuthService;
import com.zut.admin.service.SysUserService;
import com.zut.common.result.AjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2023-04-14 16:31 星期五
 * @Author: 聂建强
 * @Description: 认证后，获取信息接口
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private AuthService authService;

    /**
     * 获取当前登录用户的信息
     * @return
     */
    @RequestMapping("/info")
    public AjaxResult getLoginUserInfo(Principal principal,String clientCode){

        // 获取当前登录的用户名
        String username = principal.getName();

        // // 获取登录用户信息
        SysUser sysUser = sysUserService.selectSysUserByUsername(username);


        System.out.println("clientCode:"+clientCode);

        // // 权限列表
        List<String> permissionList = authService.getUserInfo(sysUser.getUserId(),clientCode);

        // String [] permissionArray = permissionList.toArray(new String[permissionList.size()]);

        Map map=new HashMap();


        // map.put("roles","[admin]");    //角色信息
        // map.put("name",sysUser.getNickname()); //用户昵称
        // map.put("avatar",sysUser.getAvatar()); //用户头像信息

        map.put("roles",permissionList);     //角色信息
        map.put("name",username); //用户昵称
        map.put("avatar",sysUser.getAvatar()); //用户头像信息

        return  AjaxResult.ok(200,map);

    }

}
