package com.zut.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zut.admin.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Date 2023-04-14 20:02 星期五
 * @Author: 聂建强
 * @Description:
 */

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {


    List<String> selectPermsList(@Param(value = "userId")Long userId,@Param(value = "clientCode") String clientCode);

    List<Menu> getMenuListByUserId(Long userId);
}
