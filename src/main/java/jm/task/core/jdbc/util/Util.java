package jm.task.core.jdbc.util;
import java.sql.*;
import java.sql.Driver;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/kata_db";
    private static final String USER = "root";
    private static final String PASSWORD = "12TSsKt@";

    public static Connection getConnection() {
        Connection connection = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Connection successful!");
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection failed!");
        }

        return connection;
    }
}
