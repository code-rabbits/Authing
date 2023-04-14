package com.zut.admin.utils;

import com.zut.admin.entity.Menu;
import com.zut.admin.vo.MenuVo;
import com.zut.admin.vo.RouterVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Date 2023-04-14 21:37 星期五
 * @Author: 聂建强
 * @Description: 将菜单封装成菜单树
 */
public class MakeMenuTree {

    // 将数据组装成菜单树
    public static List<MenuVo> makeTree(List<MenuVo> menuList, Long pid){
        List<MenuVo> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null &&  item.getParentId() == pid)
                .forEach(item -> {
                    MenuVo menu = new MenuVo();
                    BeanUtils.copyProperties(item, menu);
                    List<MenuVo> children = makeTree(menuList,item.getId());
                    menu.setChildren(children);
                    list.add(menu);
                });
        return list;
    }
    // 递归生成路由
    public static List<RouterVO> makeRouter(List<Menu> menuList, Long parentId) {
        List<RouterVO> list = new ArrayList<>();
        Optional.ofNullable(menuList).ifPresent(menus -> menus.stream().filter(menu -> menu.getParentId() == parentId)
                .forEach(menu -> {
                    RouterVO routerVO = new RouterVO();
                    routerVO.setName(menu.getMenuName());
                    routerVO.setPath(menu.getPath());
                    if (parentId == 0L) {
                        routerVO.setComponent("Layout");
                        routerVO.setAlwaysShow(true);
                    } else {
                        routerVO.setComponent(menu.getComponent());
                        //设为true时，将会展示为上级菜单
                        routerVO.setAlwaysShow(false);
                    }
                    routerVO.setMeta(routerVO.new Meta(
                            menu.getMenuName(),
                            menu.getIcon(),
                            menu.getPerms().split(",")
                    ));
                    // 菜单显示隐藏
                    List<RouterVO> children = makeRouter( menuList,menu.getMenuId());
                    routerVO.setChildren(children);
                    list.add(routerVO);
                }));
        return list;
    }

}
