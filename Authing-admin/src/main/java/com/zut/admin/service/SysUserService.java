package com.zut.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zut.admin.entity.SysUser;
import com.zut.common.page.DataTableResult;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2022-05-06
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser selectSysUserByUsername(String username);

    /**
     * 获取用户权限信息（角色、菜单权限）
     * @param userId
     * @return
     */
    List<GrantedAuthority> selectUserAuthorityList(Long userId);

}
