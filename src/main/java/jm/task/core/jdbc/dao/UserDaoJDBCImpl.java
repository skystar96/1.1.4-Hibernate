package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    // Все исключения ловятся в классе Dao

    private String NAME, LASTNAME;
    private byte AGE;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        try (Connection connect = Util.getConnection();
             Statement statement = connect.createStatement()){
            statement.execute("CREATE TABLE IF NOT EXISTS userstable (\n" +
                    "  `id` BIGINT(18) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(20) NOT NULL,\n" +
                    "  `lastName` VARCHAR(20) NULL,\n" +
                    "  `age` TINYINT(3) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8");
        }
    }

    public void dropUsersTable() throws SQLException {
        try (Connection connect = Util.getConnection();
             Statement statement = connect.createStatement()){
            statement.execute("DROP TABLE IF EXISTS userstable");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        this.NAME = name;
        this.LASTNAME = lastName;
        this.AGE = age;
        try (Connection connect = Util.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(
                     "INSERT INTO userstable(name, lastName, age) VALUES (?, ?, ?)")    ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();

        } catch (SQLException ignore) {
        }
    }

    public void removeUserById(long id) {
        try (Connection connect = Util.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(
                     "DELETE FROM userstable WHERE id = ?")     ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ignore) {
            throw new RuntimeException(ignore);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connect = Util.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM userstable")) {
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                User user = new User();
                user.setId(res.getLong(1));
                user.setName(res.getString(2));
                user.setLastName(res.getString(3));
                user.setAge(res.getByte(4));
                users.add(user);
            }
        } catch (SQLException ignore) {
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connect = Util.getConnection();
             Statement statement = connect.createStatement()){
            statement.execute("DELETE FROM userstable");
        } catch (SQLException ignore) {
        }
    }
}