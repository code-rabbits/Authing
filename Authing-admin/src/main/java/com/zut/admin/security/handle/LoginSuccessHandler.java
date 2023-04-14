package com.zut.admin.security.handle;


import cn.hutool.json.JSONUtil;
import com.zut.admin.security.util.JwtTokenUtil;
import com.zut.common.result.AjaxResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理器
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Resource
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * 登录成功
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		response.setContentType("application/json;charset=UTF-8");
		ServletOutputStream outputStream = response.getOutputStream();

		// 获取登录用户信息
		UserDetails principal = (UserDetails)authentication.getPrincipal();  //用户信息

		// 生成token
		String token = jwtTokenUtil.createToken(principal.getUsername());

		// 登录成功,生成jwt，并放置到请求头中
		// response.setHeader(jwtTokenUtil.getHeader(),token);

		Map map=new HashMap();

		map.put("token",token);

		System.out.println("------------登录成功");

		AjaxResult result = AjaxResult.ok(map);   //往前端返回token


		// //将生成的authentication放入容器中，生成安全的上下文
		// SecurityContextHolder.getContext().setAuthentication(authentication);

		outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

		outputStream.flush();
		outputStream.close();

	}
}
