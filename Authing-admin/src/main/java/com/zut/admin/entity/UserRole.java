package com.zut.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Date 2023-04-14 19:52 星期五
 * @Author: 聂建强
 * @Description:
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user_role")
public class UserRole {

    @TableField("user_id")
    private Long userId;

    @TableField("role_id")
    private Long roleId;

    @TableField("client_code")
    private String clientCode;

}
