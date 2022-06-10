package com.medical_store_management_system.Database_Connectivity;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Database_Connection_Test {
    public static boolean testDatabaseConnection()
    {
        boolean connectionStatus=false;
        try{
            //Step#1
            Class.forName("com.mysql.cj.jdbc.Driver");
//            Step#2
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:4399/Medical_Store_Management_System","root","hello123");

            System.out.println("Connect to Database Medical_Store_Management_System: Success");
            //Step#3
            Statement stmt =con.createStatement();

            //Step#4
            con.close();
            connectionStatus=true;
        }
        catch (Exception e)
        {
            System.out.println(e);
            connectionStatus=false;
        }
    return connectionStatus;
    }
}
