package com.zut.admin.security.filter;

import cn.hutool.core.util.StrUtil;
import com.zut.admin.entity.SysUser;
import com.zut.admin.security.util.JwtProperties;
import com.zut.admin.security.util.JwtTokenUtil;
import com.zut.admin.service.SysUserService;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *  jwt的二次身份验证
 *
 *  这个过滤器的主要作用是为了在用户登录并获取到我们发配的token之后，在带着token发送请求时，我们要检验token，
 *  判断它是否携带着token，token是否过期，token中的用户是否包含在我们的数据库中等等，
 *  如果token有效，则直接让Spring Security形成安全上下文，不再进行验证
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private SysUserService sysUserService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String requestUrl = request.getRequestURI();

        String token = request.getHeader(jwtProperties.getHeader());

        System.out.println("helooo   i"+ token);

        // 获取token
        // String token = request.getHeader(jwtTokenUtil.getHeader());

        //没有token
        if (StrUtil.isBlankOrUndefined(token)) {
            chain.doFilter(request, response);   //直接让过滤器链往下走
            return;
        }
        //token 为空
        if (token==null){
            throw new JwtException("token 异常");
        }
        //token过期
        // if (jwtTokenUtil.isTokenExpired(token)){
        //     throw new JwtException("token已过期");
        // }
        // 根据令牌获取登录认证信息
        String username = jwtTokenUtil.getUsernameFromToken(token);

        // 获取登录用户的信息
        SysUser sysUser = sysUserService.selectSysUserByUsername(username);

        //获取用户权限信息
        // List<GrantedAuthority> grantedAuthorityList = sysUserService.selectUserAuthorityList(sysUser.getUserId());

        //将登录用户信息交给SpringSecurity处理
        UsernamePasswordAuthenticationToken AuthenticationToken
                = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());

        SecurityContextHolder.getContext().setAuthentication(AuthenticationToken);

        chain.doFilter(request, response);

    }
}
