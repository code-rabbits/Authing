package com.zut.admin.security.detail;

import com.alibaba.fastjson.JSON;
import com.zut.admin.entity.SysUser;
import com.zut.admin.mapper.SysUserMapper;
import com.zut.admin.service.SysUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 自定义SpringSecurity的登录逻辑
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Resource
	private SysUserService sysUserService;

	@Resource
	private SysUserMapper sysUserMapper;

	/**
	 * 重写
	 * @param s  AuthParamsDto 类型的JSON数据,不仅仅是username
	 * @return  UserDetails
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

		// // 将传入的JSON串转成AuthParamsDto对象
		// AuthParamsDto authParamsDto=null;
		//
		// try {
		// 	authParamsDto = JSON.parseObject(s, AuthParamsDto.class);
		//
		// }catch (Exception e){
		// 	throw new RuntimeException("请求认证参数不符合要求！");
		// }
		//
		// // 账号
		// String username=authParamsDto.getUsername();
		//
		// SysUser sysUser = sysUserService.selectSysUserByUsername(username);


		System.out.println("----"+s+"---");

		SysUser sysUser = sysUserService.selectSysUserByUsername(s);
		// sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername,username));

		if (sysUser==null){
			throw new UsernameNotFoundException("用户名不存在");
		}

		// 密码
		String password = sysUser.getPassword();

		// 密码置空
		sysUser.setPassword(null);
		// 将用户信息转JSON
		String userJson= JSON.toJSONString(sysUser);

		//权限列表
		List<GrantedAuthority> grantedAuthorityList = sysUserService.selectUserAuthorityList(sysUser.getUserId());

		return new User(s,password,grantedAuthorityList);
		// return new User(userJson,password,grantedAuthorityList);
	}


}
