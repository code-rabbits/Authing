package com.zut.admin.controller;

import com.zut.admin.entity.Menu;
import com.zut.admin.entity.SysUser;
import com.zut.admin.service.RouterService;
import com.zut.admin.service.SysUserService;
import com.zut.admin.utils.MakeMenuTree;
import com.zut.admin.vo.RouterVO;
import com.zut.common.constants.GlobalConstants;
import com.zut.common.result.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

/**
 * @Date 2023-04-14 21:30 星期五
 * @Author: 聂建强
 * @Description: 动态路由的controller
 */

@RestController
@RequestMapping("/router")
public class RouterController {

    @Resource
    private RouterService routerService;

    @Resource
    private SysUserService userService;


    /**
     * 获取当前登录用户的动态路由
     * @return
     */
    @GetMapping("/getRouteList")
    public AjaxResult getRouteList(Principal principal){
        // 获取当前登录用户的信息
        String principalName = principal.getName();
        SysUser user = userService.selectSysUserByUsername(principalName);

        //获取登录用户的权限(只需要获取目录和菜单权限即可，用于生成路由)
        List<Menu> menuList = routerService.getMenuListByUserId(user.getUserId());

        System.out.println("AAA"+menuList+"bbb");

        // 将权限封装成路由的形式
        List<RouterVO> routerVOList = MakeMenuTree.makeRouter(menuList, GlobalConstants.ROOT_MENU_ID);

        return AjaxResult.ok(routerVOList);
    }

}
