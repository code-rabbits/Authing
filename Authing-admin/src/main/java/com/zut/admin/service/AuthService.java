package com.zut.admin.service;

import com.zut.admin.mapper.MenuMapper;
import com.zut.admin.mapper.RoleMapper;
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
    private RoleMapper roleMapper;

    @Resource
    private MenuMapper menuMapper;

    /**
     * 获取登录用户的权限编码
     *
     * @return UserVo
     */
    public List<String> getUserInfo(Long userId, String clientCode){

        // 获取角色权限编码列表
        List<String> codeList = roleMapper.getRoleCodeList(userId,clientCode);

        // 获取菜单权限编码列表
        List<String> permsList = menuMapper.selectPermsList(userId,clientCode);

        // 最终的权限列表
        List<String> permissionList=new ArrayList<>();

        for (String s:codeList){
            permissionList.add(s);
        }
        for (String p:permsList ){
            permissionList.add(p);
        }

        return permissionList;
    }



}
