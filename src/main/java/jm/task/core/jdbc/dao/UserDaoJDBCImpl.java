package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String method = "createUsersTable";
        boolean isNotComplete;
        int count = 0;
        do {
            try (Statement statement = Util.getConnection().createStatement()) {
                System.out.println("Start " + method);
                statement.execute("CREATE TABLE IF NOT EXISTS users2 (\n" +
                        "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` VARCHAR(45) NOT NULL,\n" +
                        "  `last_name` VARCHAR(45) NOT NULL,\n" +
                        "  `age` INT(3) NOT NULL,\n" +
                        "  PRIMARY KEY (`id`));");
                System.out.println("Should be executed");
                isNotComplete = false;
            } catch (SQLException e) {
                isNotComplete = true;
                count++;
                if (count >= 3) {
                    throw new RuntimeException(e);
                }
            }
        } while (isNotComplete);
    }

    public void dropUsersTable() {
        String method = "dropUsersTable";
        boolean isNotComplete;
        int count = 0;
        do {
            try (Statement statement = Util.getConnection().createStatement()) {
                System.out.println("Start " + method);
                statement.execute("DROP TABLE IF EXISTS users2;");
                System.out.println("Should be executed");
                isNotComplete = false;
            } catch (SQLException e) {
                isNotComplete = true;
                count++;
                if (count >= 3) {
                    throw new RuntimeException(e);
                }
            }
        } while (isNotComplete);
    }

    public void saveUser(String name, String lastName, byte age) {
        String method = "saveUser";
        String insert = "INSERT INTO users2 (name, last_name, age) VALUES (?, ?, ?);";
        boolean isNotComplete;
        int count = 0;
        do {
            try (PreparedStatement statement = Util.getConnection().prepareStatement(insert)) {
                System.out.println("Start " + method);
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setInt(3, age);
                statement.executeUpdate();
                System.out.println("Should be executed");
                isNotComplete = false;
            } catch (SQLException e) {
                isNotComplete = true;
                count++;
                if (count >= 3) {
                    throw new RuntimeException(e);
                }
            }
        } while (isNotComplete);
    }

    public void removeUserById(long id) {
        String method = "removeUserById";
        boolean isNotComplete;
        int count = 0;
        do {
            try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(
                    "DELETE FROM users2 WHERE id = ?")) {
                System.out.println("Start " + method);
                preparedStatement.setInt(1, (int) id);
                preparedStatement.execute();
                System.out.println("Should be executed");
                isNotComplete = false;
            } catch (SQLException e) {
                isNotComplete = true;
                count++;
                if (count >= 3) {
                    throw new RuntimeException(e);
                }
            }
        } while (isNotComplete);
    }

    public List<User> getAllUsers() {
        String method = "getAllUsers";
        List<User> users = null;
        boolean isNotComplete;
        int count = 0;
        do {
            try (Statement statement = Util.getConnection().createStatement()) {
                System.out.println("Start " + method);
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users2;");
                users = getUsers(resultSet);
                System.out.println("Should be executed");
                isNotComplete = false;
            } catch (SQLException e) {
                isNotComplete = true;
                count++;
                if (count >= 3) {
                    throw new RuntimeException(e);
                }
            }
        } while (isNotComplete);
        return users;
    }

    public void cleanUsersTable() {
        String method = "cleanUsersTable";
        boolean isNotComplete;
        int count = 0;
        do {
            try (Statement statement = Util.getConnection().createStatement()) {
                System.out.println("Start " + method);
                statement.execute("TRUNCATE TABLE users2;");
                System.out.println("Should be executed");
                isNotComplete = false;
            } catch (SQLException e) {
                isNotComplete = true;
                count++;
                if (count >= 3) {
                    throw new RuntimeException(e);
                }
            }
        } while (isNotComplete);
    }

    private List<User> getUsers(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setAge(resultSet.getByte("age"));
            users.add(user);
        }
        return users;
    }
}
