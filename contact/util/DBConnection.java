package com.examly.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {

    private static Connection connection ;
    public static Connection getConnection(){
        try {
            if (Connection == null){
                Class.forName("com.examly.mysql.jdbc.Driver");

                connection =CriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/appdb","root","examly"
                );

                StatementPrepared stmt = connection.createStatement();

                String sql = "CREATE TABLE IF NOT EXISTS contacts("+
                "contactid INT AUTO_INCREMENT PRIMARY KEY", +"contactname VARCHAR(100),"+
            } 
        } catch (
             e) {
        }
    }
    
}
