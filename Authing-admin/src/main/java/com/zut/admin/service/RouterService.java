package com.zut.admin.service;

import com.zut.admin.entity.Menu;
import com.zut.admin.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Date 2023-04-14 21:31 星期五
 * @Author: 聂建强
 * @Description:
 */

@Service
public class RouterService {

    @Resource
    private MenuMapper menuMapper;


    /**
     * 获取登录用户的权限(只需要获取目录和菜单权限即可，用于生成路由)
     * @param userId
     * @return
     */
    public List<Menu> getMenuListByUserId(Long userId){

        return menuMapper.getMenuListByUserId(userId);
    }
}
