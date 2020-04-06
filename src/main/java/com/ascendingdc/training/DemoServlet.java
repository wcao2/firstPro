package com.ascendingdc.training;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DemoServlet extends HttpServlet {
    private static final long serialVersionUID=1L;
    //一个docke部署一个tomcat,再部署一个servlet
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        out.println("Welcome ASCENDING");
        System.out.println("We are in HelloServlet");
    }
}
