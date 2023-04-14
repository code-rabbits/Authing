package com.zut.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Date 2023-04-14 20:07 星期五
 * @Author: 聂建强
 * @Description:
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_role_menu")
public class RoleMenu extends BaseEntity{

    @TableField("role_id")
    private Long roleId;

    @TableField("menu_id")
    private Long menuId;


    @TableField("client_code")
    private String clientCode;


}
