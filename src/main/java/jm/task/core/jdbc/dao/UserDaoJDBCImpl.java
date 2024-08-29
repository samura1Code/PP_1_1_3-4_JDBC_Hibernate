package jm.task.core.jdbc.dao;

import com.mysql.cj.jdbc.StatementImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `users` ("
                    + "`id` BIGINT NOT NULL AUTO_INCREMENT, "
                    + "`name` VARCHAR(45) NOT NULL, "
                    + "`lastname` VARCHAR(45) NOT NULL, "
                    + "`age` TINYINT NOT NULL, "
                    + "PRIMARY KEY (`id`))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch (SQLException e) {
            System.out.println("Error occurred while trying to create the table");
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE `kata_db`.`users`";
        try (Connection conn = Util.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
             preparedStatement.executeUpdate();
             System.out.println("Table dropped successfully.");
        } catch (SQLException e) {
            System.out.println("Error occurred while trying to drop the table");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES(?, ?, ?)";
        try (Connection conn = Util.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
             preparedStatement.setString(1, name);
             preparedStatement.setString(2, lastName);
             preparedStatement.setByte(3, (byte) age);
             preparedStatement.executeUpdate();
             System.out.println("User added successfully. User with name '" + name + "' added to the database.");
        } catch (SQLException e) {
            System.out.println("Error occurred while trying to add user");
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE fROM users where id = '" + id + "'";
        try (Connection conn = Util.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
             int resultOutput = preparedStatement.executeUpdate();
             if (resultOutput == 0) {
                 System.out.println("User with id " + id + " not found.");
             }
             else {
                 System.out.println("User with id " + id + " has been removed successfully.");
             }
        } catch (SQLException e) {
            System.out.println("Error occurred while trying to remove user");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String SQL = "SELECT * FROM users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL);) {
            if (resultSet != null) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastname"));
                    user.setAge(resultSet.getByte("age"));
                    users.add(user);
                }
            } else {
                System.out.println("No users found in the table.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user list, there was a problem fetching data from the database.");
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM `kata_db`.`users`";
        try (Connection conn = Util.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
             preparedStatement.executeUpdate();
            System.out.println("Table cleared successfully.....");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


