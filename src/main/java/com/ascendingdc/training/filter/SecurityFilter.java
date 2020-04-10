package com.ascendingdc.training.filter;

import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.service.EmployeeService;
import com.ascendingdc.training.service.JWTService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "securityFilter",urlPatterns = {"/*"},dispatcherTypes = {DispatcherType.REQUEST})
public class SecurityFilter implements Filter {

    private static final Set<String> IGNORED_PATHS=new HashSet<>(Arrays.asList("/auth"));
    @Autowired
    private JWTService jwtService;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;//once the request not based on httprequest
        int statusCode=authorization(req);
        if(statusCode==HttpServletResponse.SC_ACCEPTED) filterChain.doFilter(servletRequest,servletResponse);
        else ((HttpServletResponse)servletResponse).sendError(statusCode);

        //filterChain.doFilter(servletRequest,servletResponse);

    }

    private int authorization(HttpServletRequest req){
        int statusCode=HttpServletResponse.SC_UNAUTHORIZED;//401
        String uri=req.getRequestURI();
        String verb=req.getMethod();
        if(IGNORED_PATHS.contains(uri)) return HttpServletResponse.SC_ACCEPTED;//202

        try{
            String token=req.getHeader("Authorization").replaceAll("^(.*?) ","");
            if(token==null||token.isEmpty()) return statusCode;

            Claims claims=jwtService.decyptToken(token);
            if(claims.getId()!=null){
                Employee e=employeeService.getEmployeeById(Long.valueOf(claims.getId()));
                if(e !=null) statusCode=HttpServletResponse.SC_ACCEPTED;
            }
        }catch (Exception e){
            //if token is null
            e.printStackTrace();
        }
        return statusCode;
    }

    @Override
    public void destroy() {

    }
}
