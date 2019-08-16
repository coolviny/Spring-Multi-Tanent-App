package com.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class DataSourceIntercetor extends HandlerInterceptorAdapter {
   
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	String tanent = request.getHeader("X-TenantID");
        System.out.println("Request tanent ="+tanent);
        request.setAttribute("keyDS", tanent);
 
        return true;
    }
}
