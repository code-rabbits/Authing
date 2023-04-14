package com.zut.admin.security.handle;

import cn.hutool.json.JSONUtil;
import com.zut.common.result.AjaxResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *自定义权限不足处理类
 */
@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");   //返回json格式的数据
        ServletOutputStream outputStream = response.getOutputStream();

        AjaxResult result = AjaxResult.fail("无权限访问,请联系管理员！");  //返回前端的信息

        outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

        outputStream.flush();
        outputStream.close();

    }
}
