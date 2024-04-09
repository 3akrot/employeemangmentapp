package com.example.employeemangmentapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    static String host = "localhost";
    static int port = 3306;
    static String DBname = "emloyeee";
    static String Username = "root";
    static String pass = "";
    static Connection connection;
    static Connection getconncetion()  {
        try{
            connection =  DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+DBname,Username,pass);

        }
        catch (SQLException e){
            System.out.println("Connection Error");
            e.printStackTrace();
        }

        return connection;
    }
}
