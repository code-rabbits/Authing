package com.zut.admin.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Date 2023-04-14 21:39 星期五
 * @Author: 聂建强
 * @Description: 菜单的vo
 */
@Data
public class MenuVo {
    private Long id;

    private String name;

    private Long parentId;

    private String path;

    private String perms;

    private String component;

    private Integer type;

    private String icon;


    private Integer orderNum;

    private Date createTime;

    private Date updateTime;


    private Integer status;

    private List< MenuVo> children = new ArrayList<>();

}
