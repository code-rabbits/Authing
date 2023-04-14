package com.zut.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zut.admin.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Date 2023-04-14 19:48 星期五
 * @Author: 聂建强
 * @Description:
 */
@Mapper
public interface RoleMapper  extends BaseMapper<Role> {


    List<String> getRoleCodeList(@Param(value = "userId") Long userId,@Param(value = "clientCode") String clientCode);
}
