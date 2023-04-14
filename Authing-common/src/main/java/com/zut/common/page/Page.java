package com.zut.common.page;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页参数
 */
@Data
public class Page implements Serializable {

    // 当前页码
    private long pageNumber;

    // 每页大小
    private long pageSize;
}
