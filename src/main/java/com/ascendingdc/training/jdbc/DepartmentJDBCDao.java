package com.ascendingdc.training.jdbc;

import com.ascendingdc.training.model.DepartmentJDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DepartmentJDBCDao{
    private static Logger logger= LoggerFactory.getLogger(DepartmentJDBCDao.class);

    static final String DBURL = "jdbc:postgresql://localhost:5431/db_wei";
    static final String USER = "admin";
    static final String PASS = "password";

    public List<DepartmentJDBC> getDepartments(){
        List<DepartmentJDBC> departments=new ArrayList();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;

        try{
            //1
            logger.debug("connecting to database...");
            //System.out.println("connecting to database...");
            conn= DriverManager.getConnection(DBURL,USER,PASS);

            //2
            logger.info("creating statement...");
            //System.out.println("creating statement...");
            stmt=conn.createStatement();
            String sql;
            sql="SELECT * FROM department";
            logger.warn(sql);
            rs=stmt.executeQuery(sql);

            //iterator: hasNext() return true or false, next() what is the next object
            while(rs.next()){
                //Retrieve by column name
                Long id=rs.getLong("id");
                String name=rs.getString("name");
                String description=rs.getString("description");
                String location=rs.getString("location");

                //fill the object
                DepartmentJDBC department=new DepartmentJDBC();
                department.setId(id);
                department.setName(name);
                department.setLocation(location);
                department.setDescription(description);
                departments.add(department);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
           try{
               if(rs!=null) rs.close();
               if(stmt!=null) stmt.close();
               if(conn!=null) conn.close();
           }catch (Exception e){
               e.printStackTrace();
           }
        }
        return departments;
    }

    public static void main(String[] args){
        DepartmentJDBCDao deDao=new DepartmentJDBCDao();
    //waste computation cost
        System.out.println(deDao.getDepartments().size()+" test");
    }
}

