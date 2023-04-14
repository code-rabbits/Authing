package com.zut.admin.security.config;

import com.zut.admin.security.detail.UserDetailServiceImpl;
import com.zut.admin.security.filter.JwtAuthenticationFilter;
import com.zut.admin.security.handle.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * SpringSecurity配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //开启注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private LoginSuccessHandler loginSuccessHandler;

	@Resource
	private LoginFailureHandler loginFailureHandler;

	@Resource
	private JwtLogoutSuccessHandler logoutSuccessHandler;

	@Resource
	private UserAccessDeniedHandler userAccessDeniedHandler;

	@Resource
	private UserAuthenticationEntryPoint userAuthenticationEntryPoint;


	@Resource
	private UserDetailServiceImpl userDetailService;


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}



	/**
	 * 自己的jwt过滤器
	 * @return
	 * @throws Exception
	 */
	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
		return jwtAuthenticationFilter;
	}


	/**
	 * 密码加密方式
	 * @return
	 */
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


	//不需要拦截的请求名单(白名单)
	private static final String[] URL_WHITELIST = {
			"/auth/**"
			// "/test/**",

	};


	/**
	 * security配置
	 * @param http
	 * @throws Exception
	 */
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable()

				// 登录配置
				.formLogin()
				.loginProcessingUrl("/auth/login")  //登录接口url
				.successHandler(loginSuccessHandler)   //登录成功处理器
				.failureHandler(loginFailureHandler)   //登录失败处理器

				// 退出登录配置
				.and()
				.logout()
				.logoutUrl("/auth/logout")  //退出接口url
				.logoutSuccessHandler(logoutSuccessHandler)  //退出成功处理器

				.and()
				.exceptionHandling()
				.authenticationEntryPoint(userAuthenticationEntryPoint)  //匿名用户访问无权限资源时的异常处理
				.accessDeniedHandler(userAccessDeniedHandler)  //权限不足处理器

				// 禁用session
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

				// 配置拦截规则
				.and()
				.authorizeRequests()
				.antMatchers(URL_WHITELIST).permitAll()   //不需要拦截的请求，用了上面的白名单
				.anyRequest().authenticated()  //其余请求都需要拦截

				.and()
				.addFilter(jwtAuthenticationFilter())  //将自己的jwt过滤器加入到springSecurity的过滤器链中
		;

	}

	/**
	 * 使用自定义的userDetailService
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
	}
}
