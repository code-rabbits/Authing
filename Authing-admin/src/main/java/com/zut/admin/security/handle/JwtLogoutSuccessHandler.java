package com.zut.admin.security.handle;

import cn.hutool.json.JSONUtil;
import com.zut.admin.security.util.JwtTokenUtil;
import com.zut.admin.security.util.RedisUtil;
import com.zut.common.result.AjaxResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出成功处理器
 */
@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

	@Resource
	private RedisUtil redisUtil;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		// // 获取登录用户信息
		// UserDetails principal = (UserDetails)authentication.getPrincipal();
		//
		// System.out.println("退出登录："+principal.getUsername());
		//
		// // 删除redis中的token
		// redisUtil.del(principal.getUsername());

		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		response.setContentType("application/json;charset=UTF-8");
		ServletOutputStream outputStream = response.getOutputStream();

		AjaxResult result = AjaxResult.ok("");

		outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

		outputStream.flush();
		outputStream.close();
	}
}
