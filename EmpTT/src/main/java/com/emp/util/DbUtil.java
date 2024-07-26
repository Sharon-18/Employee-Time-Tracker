package com.emp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/Emp";
    private static final String USER = "root";
    private static final String PASSWORD = "sh123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
