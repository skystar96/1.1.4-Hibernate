package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String URL = "jdbc:mysql://localhost:3306/users";
    private static String UserName = "root";
    private static String Password = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, UserName, Password);
    }
}

