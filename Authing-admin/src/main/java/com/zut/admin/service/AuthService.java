package com.zut.admin.service;

import com.zut.admin.entity.SysUser;
import com.zut.admin.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2023-04-14 17:11 星期五
 * @Author: 聂建强
 * @Description:
 */

@Service
public class AuthService {


    @Resource
    private SysUserMapper userMapper;

    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private SysMenuMapper menuMapper;



    /**
     * 获取登录用户的详细信息
     *
     * @return UserVo
     */
    public List<String> getUserInfo(Long userId, String clientCode){


        // // 查询用户信息
        // SysUser sysUser = userMapper.selectById(userId);
        //
        // // 获取角色权限编码列表
        // List<String> codeList = roleMapper.getRoleCodeList(userId);
        // // 获取菜单权限编码列表
        // List<String> permsList = menuMapper.selectPermsList(userId);
        //
        // // 最终的权限列表
        // List<String> permissionList=new ArrayList<>();
        //
        // for (String s:codeList){
        //     permissionList.add(s);
        // }
        // for (String p:permsList ){
        //     permissionList.add(p);
        // }
        //
        // return permissionList;






        return null;
    }



}
