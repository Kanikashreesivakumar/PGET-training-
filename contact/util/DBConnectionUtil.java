package com.examly.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnectionUtil {

    private static Connection connection;

    public static Connection getConnection() {

        try {
            if (connection == null) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/appdb",
                        "root",
                        "examly"
                );

                // 🔥 CREATE TABLE (IMPORTANT)
                Statement stmt = connection.createStatement();

                String sql = "CREATE TABLE IF NOT EXISTS contacts (" +
                        "contactid INT AUTO_INCREMENT PRIMARY KEY," +
                        "contactname VARCHAR(100)," +
                        "phonenumber VARCHAR(20)," +
                        "status VARCHAR(100)," +
                        "lastseen VARCHAR(50)," +
                        "blocked BOOLEAN" +
                        ")";

                stmt.execute(sql);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}