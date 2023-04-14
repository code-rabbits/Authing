package com.zut.admin.security.filter;

import com.zut.admin.security.util.JwtProperties;
import com.zut.admin.security.util.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Autowired
    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取token
        String token = request.getHeader(jwtProperties.getHeader());

        if (token != null && StringUtils.isNotEmpty(token)){

            String username = jwtTokenUtil.getUsernameFromToken(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // 通过用户名 获取用户的信息
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                // 验证token和用户是否匹配
                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    // 然后把构造UsernamePasswordAuthenticationToken对象
                    // 最后绑定到当前request中，在后面的请求中就可以获取用户信息
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        }
        chain.doFilter(request, response);

    }
}
