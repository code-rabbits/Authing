package com.zut.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zut.admin.entity.SysUser;
import com.zut.admin.security.dto.UserUpdateProfileDTO;
import com.zut.admin.security.dto.UserUpdatePwdDTO;
import com.zut.admin.vo.UserProfileVO;
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

    /**
     * 个人中心详情
     * @param userId
     * @return
     */
    UserProfileVO getUserProfile(Long userId);


    /**
     * 修改个人信息
     *
     * @param userUpdateProfileDTO
     */
    void updateProfile(UserUpdateProfileDTO userUpdateProfileDTO);


    /**
     * 修改个人密码
     * @param userUpdatePwdDTO
     */
    void updatePwd(UserUpdatePwdDTO userUpdatePwdDTO);



    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar 头像地址
     * @return 结果
     */
    boolean updateUserAvatar(String userName, String avatar);











}
