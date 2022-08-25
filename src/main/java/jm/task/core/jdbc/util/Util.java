package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Util {
    private static  Connection connection = null;
    private static final String URl = "jdbc:mysql://localhost:3306/sakila";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URl, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Connection OK");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }
    public static void closeConnection() {
        try {
            if (!connection.isClosed() || connection == null) {
                connection.close();
                System.out.println("Connection CLOSE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection was NOT created");
        }

    }

}

