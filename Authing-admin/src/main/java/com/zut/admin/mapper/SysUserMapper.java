package com.zut.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zut.admin.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author
 * @since 2022-05-06
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser selectSysUserByUsername(String username);

    /**
     * 获取用户权限列表
     * @param userId
     * @return
     */
    String getUserAuthorityList(Long userId);













    /**
     * 更新用户头像
     * @param userName
     * @param avatar
     * @return
     */
    int updateUserAvatar(@Param("userName") String userName, @Param("avatar") String avatar);
}
