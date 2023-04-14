package com.zut.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zut.admin.entity.SysUser;
import com.zut.admin.mapper.SysUserMapper;
import com.zut.admin.service.SysUserService;
import com.zut.common.page.DataTableResult;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  用户 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-06
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;


    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public SysUser selectSysUserByUsername(String username) {
        return sysUserMapper.selectSysUserByUsername(username);
    }


    /**
     * 获取用户权限信息列表
     * @param userId
     * @return
     */
    @Override
    public List<GrantedAuthority> selectUserAuthorityList(Long userId) {

        // // 查询用户信息
        // SysUser sysUser = sysUserMapper.selectById(userId);
        //
        String authority = "admin";  //定义一个权限字符串
        //
        // // 获取角色列表
        // List<SysRole> roleList = sysRoleService.selectRoleListByUserId(userId);
        // // 获取角色编码
        // if (roleList.size() > 0) {
        //     String roleCodes = roleList.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
        //     authority = roleCodes.concat(",");
        // }
        //
        // // 获取菜单列表
        // List<SysMenu> sysMenuList = sysMenuService.selectMenuList(userId);
        // // 获取菜单权限编码
        // if (sysMenuList.size() > 0) {
        //     String menuPerms = sysMenuList.stream().map(m -> m.getPerms()).collect(Collectors.joining(","));
        //     authority = authority.concat(menuPerms);
        // }


        // 将角色编码和菜单权限编码封装成SpringSecurity要求的格式
        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
        //
        return grantedAuthorityList;
    }

}
