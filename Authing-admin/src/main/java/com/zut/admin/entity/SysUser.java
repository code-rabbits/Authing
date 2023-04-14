package com.zut.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @Date 2023-04-14 10:50 星期五
 * @Author: 聂建强
 * @Description:
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_sys_user")
public class SysUser {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;


    private Long deptId;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    @TableField("user_type")
    private String userType;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 性别：0-男 1-女
     */
    @TableField("gender")
    private String sex;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 邮箱
     */
    @TableField("email")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;


    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 用户状态：1-正常 0-禁用
     */
    @TableField("status")
    private Integer status;


    @TableField("remark")
    private String remark;

}
