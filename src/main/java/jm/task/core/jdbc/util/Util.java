package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
    private static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://localhost:3306/kata_db";
    private static String USER = "root";
    private static String PASS = "12TSsKt@";

    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("DB Driver not found", e);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
}


