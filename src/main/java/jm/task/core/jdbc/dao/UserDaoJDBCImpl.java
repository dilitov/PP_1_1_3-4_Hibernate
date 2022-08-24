package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();




    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String newTable = "CREATE TABLE IF NOT EXISTS sakila.users ( Id INT UNSIGNED NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(20)  NOT NULL, lastName VARCHAR(20)  NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id));";
        try (Statement statement = connection.createStatement()) {
            statement.execute(newTable);
            System.out.println("Table was create");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String dropTableUsers = "DROP TABLE IF EXISTS sakila.users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(dropTableUsers);
            System.out.println("Table was delete");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO sakila.Users (name, LastName, age) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User " + name + " was create");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM sakila.users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User " + id + " was delete");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age from sakila.users";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        String cleanTable = "TRUNCATE TABLE sakila.users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(cleanTable);
            System.out.println("Table was clean");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
