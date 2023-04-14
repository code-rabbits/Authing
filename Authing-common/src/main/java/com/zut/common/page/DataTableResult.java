package com.zut.common.page;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 往前端返回的数据表格的统一格式
 */
@Data
@AllArgsConstructor
public class DataTableResult<T> {

    private Long total;  //总条数
    private Long pageNumber; //页码
    private Long pageSize; //每页大小
    private List<T> list;  //数据

}
