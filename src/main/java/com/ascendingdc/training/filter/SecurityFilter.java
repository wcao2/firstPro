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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "securityFilter",urlPatterns = {"/*"},dispatcherTypes = {DispatcherType.REQUEST})
public class SecurityFilter implements Filter {

    private static final Set<String> IGNORED_PATHS=new HashSet<>(Arrays.asList("/auth","/auth/signup"));
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
        //if accepted, go to the next filter
        if(statusCode==HttpServletResponse.SC_ACCEPTED) filterChain.doFilter(servletRequest,servletResponse);
        else ((HttpServletResponse)servletResponse).sendError(statusCode);


    }

    private int authorization(HttpServletRequest req){
        int statusCode=HttpServletResponse.SC_UNAUTHORIZED;//401
        String uri=req.getRequestURI();
        String verb=req.getMethod();
        HttpSession session=req.getSession();
        //if it is log in
        if(IGNORED_PATHS.contains(uri)) return HttpServletResponse.SC_ACCEPTED;//202

        try{
            String token=req.getHeader("Authorization").replaceAll("^(.*?) ","");//?作用
            if(token==null||token.isEmpty()) return statusCode;

            Claims claims=jwtService.decyptToken(token);
            Employee e=employeeService.getEmployeeById(Long.valueOf(claims.getId()));
            if(e ==null){
                statusCode=HttpServletResponse.SC_UNAUTHORIZED;
            } else{
                session.setAttribute("EmployeeId",e.getId());
            }

            String allowResources="/";
            switch(verb){
                case "GET" :allowResources=(String)claims.get("allowedReadResources"); break;
                case "POST":allowResources=(String)claims.get("allowedCreateResources"); break;
                case "PUT": allowResources=(String)claims.get("allowedUpdateResources");break;
                case "DELETE":allowResources=(String)claims.get("allowDeleteResources");break;
            }
            for (String s:allowResources.split(",")){
                if(s.trim().isEmpty()){
                    statusCode=HttpServletResponse.SC_UNAUTHORIZED;
                    break;
                }else{
                    if(uri.trim().toLowerCase().startsWith(s.trim().toLowerCase())){
                        statusCode=HttpServletResponse.SC_ACCEPTED;
                        break;
                    }
                }
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
