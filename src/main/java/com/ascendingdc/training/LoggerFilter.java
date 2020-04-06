package com.ascendingdc.training;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "loggerFilter")//like a instance
public class LoggerFilter implements Filter {
    FilterConfig filterConfig=null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(((HttpServletRequest) request).getServletPath());
        System.out.println("In LoggerFilter before doFilter");
        chain.doFilter(request,response);
        System.out.println("In LoggerFilter after doFilter");
    }

    @Override
    public void destroy() {
        //when call this method
    }
}
