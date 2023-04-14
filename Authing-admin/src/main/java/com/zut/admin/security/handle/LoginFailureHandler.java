package com.zut.admin.security.handle;

import cn.hutool.json.JSONUtil;
import com.zut.common.result.AjaxResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证失败处理类
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	/**
	 * 登录失败
	 * @param request
	 * @param response
	 * @param e
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

		response.setContentType("application/json;charset=UTF-8");   //返回json格式的数据
		ServletOutputStream outputStream = response.getOutputStream();

		AjaxResult result = AjaxResult.fail("用户名或密码错误,请重新登录");  //返回前端的信息

		outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

		outputStream.flush();
		outputStream.close();
	}

}
