package com.zut.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2023-04-14 19:58 星期五
 * @Author: 聂建强
 * @Description:
 */

@Getter
@Setter
@TableName("t_menu")
@AllArgsConstructor
@NoArgsConstructor
public class Menu  extends BaseEntity implements Serializable {

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 父菜单ID，一级菜单为0
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     *
     */
    @TableField("client_code")
    private String clientCode;

    /**
     * 菜单URL
     */
    @TableField("path")
    private String path;

    /**
     * 权限编码(多个用逗号分隔，如：user:list,user:create)
     */
    @TableField("perms")
    private String perms;

    /**
     * 组件路径
     */
    @TableField("component")
    private String component;

    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    @TableField("menu_type")
    private Integer menuType;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;


    /**
     * 状态：0-禁用 1-开启
     */
    @TableField("status")
    private Integer status;



    @TableField("remark")
    private String remark;


    @TableField("is_frame")
    private Integer isFrame;


}
