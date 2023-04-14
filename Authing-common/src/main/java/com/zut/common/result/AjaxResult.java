package com.zut.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 后端给前端返回的统一格式的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResult {

    private int code;   //码

    private String msg;  //信息

    private Object data; //数据

    public AjaxResult(int code, Object data) {
        this.code=code;
        this.data=data;

    }


    public static AjaxResult ok(Object data){
        return new AjaxResult(200,"success",data);
    }

    public static AjaxResult fail(int code, String msg){
        return new AjaxResult(code,msg,null);
    }

    public static AjaxResult fail(String msg){
        return new AjaxResult(400,msg,null);
    }

    public static AjaxResult fail(Object data){
        return new AjaxResult(400,"fail",data);
    }

    public static AjaxResult ok(int code,Object data){
        return new AjaxResult(code,data);
    }

    public static AjaxResult ok(String msg){
        return new AjaxResult(200,msg,"");
    }

    public static AjaxResult ok(String msg,Object data){
        return new AjaxResult(200,msg,data);
    }

    public static AjaxResult ok(int code,String msg,Object data){
        return new AjaxResult(code,msg,data);
    }


    public static AjaxResult ok() {
        return new AjaxResult(200,"",null);
    }
}
