package com.cryface.easyblog.shiro;

import cn.hutool.json.JSONUtil;
import com.cryface.easyblog.common.lang.Result;
import com.cryface.easyblog.uitls.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends AuthenticatingFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        if(StringUtils.isEmpty(jwt)){
            return null;
        }
        return new JwtToken(jwt);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        if(StringUtils.isEmpty(jwt)){
            // 如果token为空，直接进行拦截
             return true;
        }else{
            // 如果不为空，进行校验与执行
            Claims claimByToken = jwtUtil.getClaimByToken(jwt);
            if(claimByToken == null || jwtUtil.isTokenExpired(claimByToken.getExpiration())){
                throw new ExpiredCredentialsException("token已经失效，请重新登录");
            }
            // 如果验证通过，则进行登录操作
            return executeLogin(servletRequest,servletResponse);
        }
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        // 首先对我们的response 进行强转
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 捕捉异常，并将错误信息返回给前端
        Throwable throwable = e.getCause() == null ? e : e.getCause();

        Result result = Result.fail(throwable.getMessage());

        String json = JSONUtil.toJsonStr(result);
        try {
            httpServletResponse.getWriter().print(json);
        }catch (IOException err){
            err.printStackTrace();
        }

        return super.onLoginFailure(token,e,request,response);
    }
}
