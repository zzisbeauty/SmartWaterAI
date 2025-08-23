package com.hanwei.core.security;

import cn.hutool.core.text.AntPathMatcher;
import com.hanwei.core.common.api.vo.LoginUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest wrappedRequest = new ContentCachingRequestWrapper(request);

        //配置请求白名单，不执行以下逻辑
        if(checkWhiteList(request)){
            filterChain.doFilter(request, response);
        }else{
            String ticket = request.getHeader("ticket");
            if (ticket != null && !"".equals(ticket)) {
                LoginUser loginUser = userDetailsService.loadUserByUsername(ticket);
                if (loginUser != null ) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(loginUser,
                                    null, null);
                    // 把当前登陆用户放到上下文中
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    // 认证
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    // 用户不合法，清除上下文
                    SecurityContextHolder.clearContext();
                }
            }

            filterChain.doFilter(wrappedRequest, response);
        }


    }

    /**
     * 检查请求是否在白名单中
     * @param request
     * @return
     */
    private boolean checkWhiteList(HttpServletRequest request) {
        String uri = request.getRequestURI();
        for (String pattern : UrlWhiteList.WHITELIST) {
            if (antPathMatcher.match(pattern, uri)) {
                return true;
            }
        }
        return false;
    }

}
