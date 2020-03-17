package com.ascendingdc.training.jdbc;

import com.ascendingdc.training.model.AccountJDBC;
import com.ascendingdc.training.model.EmployeeJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountJDBCDao {
    static final String DBURL = "jdbc:postgresql://localhost:5431/db_wei";
    static final String USER = "admin";
    static final String PASS = "password";
    public boolean getAccounts(){
        List<AccountJDBC> accounts=new ArrayList();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try{
            System.out.println("connecting to database...");
            conn= DriverManager.getConnection(DBURL,USER,PASS);

            System.out.println("creating statement...");
            stmt=conn.createStatement();
            String sql;
            sql="INSERT INTO account values(100002,'normal',1000,'2020-3-4',1001)";
            stmt.executeUpdate(sql);
            conn.close();
            return true;

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args){
        AccountJDBCDao aDao=new AccountJDBCDao();
        //waste computation cost
        System.out.println(aDao.getAccounts()+" test accountDao");
    }
}
