package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private static  Connection connection = null;
    private static final String URl = "jdbc:mysql://localhost:3306/sakila";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties properties = new Properties();
                properties.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL,URl);
                properties.put(Environment.USER,USERNAME);
                properties.put(Environment.PASS,PASSWORD);
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistry service = new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(service);

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

    public static void CloseSessionFactory() {
        sessionFactory.close();
        System.out.println("SessionFactory was closed");
    }

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

