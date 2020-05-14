package com.ascendingdc.training.jdbc;

import com.ascendingdc.training.model.EmployeeJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeJDBCDao {
    static final String DBURL = "jdbc:postgresql://localhost:5431/db_wei";
    static final String USER = "admin";
    static final String PASS = "password";
    public List<EmployeeJDBC> getEmployees(){
        List<EmployeeJDBC> employees=new ArrayList();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;

        try{
            System.out.println("connecting to database...");
            conn= DriverManager.getConnection(DBURL,USER,PASS);

            System.out.println("creating statement...");
            stmt=conn.createStatement();
            String sql;
            sql="SELECT * FROM employee";
            rs=stmt.executeQuery(sql);

            //iterator: hasNext() return true or false, next() what is the next object
            while(rs.next()){
                //Retrieve by column name
                Long id=rs.getLong("id");
                String name=rs.getString("name");
                String first_name=rs.getString("first_name");
                String last_name=rs.getString("last_name");
                String email=rs.getString("email");
                String address=rs.getString("address");
                String hired_date=rs.getString("hired_date");
                Long department_id=rs.getLong("department_id");

                //fill the object
                EmployeeJDBC e=new EmployeeJDBC();
                e.setId(id);
                e.setName(name);
                e.setFirst_name(first_name);
                e.setLast_name(last_name);
                e.setEmail(email);
                e.setAddress(address);
                e.setHired_date(hired_date);
                e.setDepartment_id(department_id);
                employees.add(e);
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
        return employees;
    }

//    public static void main(String[] args){
//        EmployeeJDBCDao eDao=new EmployeeJDBCDao();
//        //waste computation cost
//        System.out.println(eDao.getEmployees().size()+" test employeeDao");
//    }
}
