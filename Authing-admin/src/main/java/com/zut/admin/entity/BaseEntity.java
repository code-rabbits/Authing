package com.zut.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2023-04-14 19:42 星期五
 * @Author: 聂建强
 * @Description:   公共实体
 */
@Data
public class BaseEntity implements Serializable {

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

}
