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
 * @Date 2023-04-14 20:09 星期五
 * @Author: 聂建强
 * @Description:
 */
@Getter
@Setter
@TableName("t_client")
@AllArgsConstructor
@NoArgsConstructor
public class Client  extends  BaseEntity{

    @TableId(value = "client_id", type = IdType.AUTO)
    private Long clientId;


    @TableField("client_code")
    private String clientCode;

    @TableField("client_name")
    private String clientName;

    @TableField("client_url")
    private String clientUrl;

    @TableField("client_icon")
    private String clientIcon;

    @TableField("sort_num")
    private Integer sortNum;

    @TableField("remark")
    private String remark;

    @TableField("status")
    private Integer status;
}
