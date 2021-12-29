package dao;

import domain.Role;
import domain.User;
import utilities.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    @Override
    public void addUser(String name, String login, String password, String dateOfBirth, String email, BigDecimal salary, ArrayList<Role> roles) {
        String sqlInsertUser = "INSERT INTO project.users (login, password, name, dateofbirth, email, salary) values (?, ?, ?, ?, ?, ?);";
        String sqlSelectUserById = "SELECT id FROM project.users WHERE login=?;";
        try (Connection connection = DBConnection.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
             PreparedStatement secondPreparedStatement = connection.prepareStatement(sqlSelectUserById)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setDate(4, java.sql.Date.valueOf(dateOfBirth));
            preparedStatement.setString(5, email);
            preparedStatement.setBigDecimal(6, salary);
            preparedStatement.executeUpdate();

            secondPreparedStatement.setString(1, login);
            ResultSet rsGetUserId = secondPreparedStatement.executeQuery();

            if (rsGetUserId.next()) {
                int id = rsGetUserId.getInt("id");
                RoleDaoSingleton.getInstance().getValue().addUserRole(id, roles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String login) {
        String sql = "DELETE FROM project.users WHERE login = ?;";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);) {
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
            System.out.println("delete: " + login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editUser(String name, String login, String password, String dateOfBirth, String email, BigDecimal salary, ArrayList<Role> roles) {
        String sqlUpdateUser = "UPDATE project.users set  password = ?, name = ?,dateofbirth=?,email=?,salary=? where login = ?;";
        String sqlSelectUserId = "SELECT id FROM project.users WHERE login = ?";


        try (Connection conn = DBConnection.getConnect();
             PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdateUser);
             PreparedStatement secondPreparedStatement = conn.prepareStatement(sqlSelectUserId);) {

            preparedStatement.setString(1, password);
            preparedStatement.setString(2, name);
            preparedStatement.setDate(3, java.sql.Date.valueOf(dateOfBirth));
            preparedStatement.setString(4, email);
            preparedStatement.setBigDecimal(5, salary);
            preparedStatement.setString(6, login);

            int rows = preparedStatement.executeUpdate();
            System.out.printf("%d rows added", rows);
            System.out.println("update user: " + login);

            secondPreparedStatement.setString(1, login);
            ResultSet rsGetUserId = secondPreparedStatement.executeQuery();

            if (rsGetUserId.next()) {
                int id = rsGetUserId.getInt("id");
                RoleDaoSingleton.getInstance().getValue().editUserRole(id, roles);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String login) {
        User user = null;
        String sqlUserSelect = "SELECT * FROM project.users WHERE login = ?;";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement firstPreparedStatement = conn.prepareStatement(sqlUserSelect)) {
            firstPreparedStatement.setString(1, login);

            ResultSet rsUserSelect = firstPreparedStatement.executeQuery();

            if (rsUserSelect.next()) {
                user = new User(
                        rsUserSelect.getString("name"),
                        rsUserSelect.getString("login"),
                        rsUserSelect.getString("password"),
                        rsUserSelect.getDate("dateOfBirth"),
                        rsUserSelect.getString("email"),
                        rsUserSelect.getBigDecimal("salary")
                );
                user.setId(rsUserSelect.getInt("id"));
                ArrayList<Role> roles = new ArrayList<>();
                RoleDaoSingleton.getInstance().getValue().getUserRoles(login, roles);
                user.setRoles(roles);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        User user;
        ArrayList<User> users = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String sqlUserSelect = "SELECT * FROM project.users;";
        try (Connection conn = DBConnection.getConnect();
             Statement stmt = conn.createStatement();) {
            ResultSet rsUsersSelect = stmt.executeQuery(sqlUserSelect);

            while (rsUsersSelect.next()) {

                user = new User(
                        rsUsersSelect.getString("name"),
                        rsUsersSelect.getString("login"),
                        rsUsersSelect.getString("password"),
                        rsUsersSelect.getDate("dateOfBirth"),
                        rsUsersSelect.getString("email"),
                        rsUsersSelect.getBigDecimal("salary")
                );
                user.setId(rsUsersSelect.getInt("id"));
                ArrayList<Role> roles = new ArrayList<>();
                RoleDaoSingleton.getInstance().getValue().getAllUsersRoles(user.getId(), roles);
                user.setRoles(roles);

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}