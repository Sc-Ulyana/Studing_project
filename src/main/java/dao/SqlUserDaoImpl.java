package dao;

import domain.Role;
import domain.User;
import utilities.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class SqlUserDaoImpl implements SqlUserDAO {
    @Override
    public void addUser(String name, String login, String password, String dateOfBirth, int age, BigDecimal salary, ArrayList<Role> roles) {
        String sql = "INSERT INTO owner.users (login, password, name, dateofbirth, age, salary) values (?, ?, ?, ?, ?, ?);";
        String sql_select_by_ID = "SELECT id FROM owner.users WHERE login=?;";
        String sql_roles = "INSERT INTO owner.user_roles (user_id, role_id) values (?, ?);";
        try (Connection conn = ConnectionPool.getConnect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             PreparedStatement secondPreparedStatement = conn.prepareStatement(sql_select_by_ID);
             PreparedStatement thirdPreparedStatement = conn.prepareStatement(sql_roles)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setDate(4, java.sql.Date.valueOf(dateOfBirth));
            preparedStatement.setInt(5, age);
            preparedStatement.setBigDecimal(6, salary);
            preparedStatement.executeUpdate();

            secondPreparedStatement.setString(1, login);
            ResultSet rs_getId = secondPreparedStatement.executeQuery();
            int id = 0;
            if (rs_getId.next()) {
                id = rs_getId.getInt("id");
            }

            int user_id = id;
            for (Role r : roles) {
                thirdPreparedStatement.setInt(1, user_id);
                thirdPreparedStatement.setInt(2, r.getId());
                thirdPreparedStatement.executeUpdate();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String login) {
        String sql = "DELETE FROM owner.users WHERE login = ?;";
        try (Connection conn = ConnectionPool.getConnect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);) {
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
            System.out.println("delete: " + login);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editUser(String name, String login, String password, String dateOfBirth, int age, BigDecimal salary, ArrayList<Role> roles) {
        String sql_update_users = "UPDATE owner.users set  password = ?, name = ?,dateofbirth=?,age=?,salary=? where login = ?;";
        String sql_select_id = "SELECT id FROM owner.users WHERE login = ?";
        String sql_delete_user_roles = "DELETE  FROM owner.user_roles WHERE user_id = ?;";
        String sql_insert_user_roles = "INSERT INTO owner.user_roles (user_id, role_id) values (?, ?);";
        ;
        try (Connection conn = ConnectionPool.getConnect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql_update_users);
             PreparedStatement secondPreparedStatement = conn.prepareStatement(sql_select_id);
             PreparedStatement thirdPreparedStatement = conn.prepareStatement(sql_delete_user_roles);
             PreparedStatement fourthPreparedStatement = conn.prepareStatement(sql_insert_user_roles)) {

            preparedStatement.setString(1, password);
            preparedStatement.setString(2, name);
            preparedStatement.setDate(3, java.sql.Date.valueOf(dateOfBirth));
            preparedStatement.setInt(4, age);
            preparedStatement.setBigDecimal(5, salary);
            preparedStatement.setString(6, login);

            int rows = preparedStatement.executeUpdate();
            System.out.printf("%d rows added", rows);
            System.out.println("update user: " + login);

            secondPreparedStatement.setString(1, login);
            ResultSet rs_getId = secondPreparedStatement.executeQuery();

            int id = 0;
            if (rs_getId.next()) {
                id = rs_getId.getInt("id");
            }

            int user_id = id;
            System.out.println(user_id);
            thirdPreparedStatement.setInt(1, user_id);
            thirdPreparedStatement.executeUpdate();

            for (Role r : roles) {
                fourthPreparedStatement.setInt(1, user_id);
                fourthPreparedStatement.setInt(2, r.getId());
                fourthPreparedStatement.executeUpdate();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User getUser(String login) {
        User usr = null;
        Role role = null;
        String sql_users = "SELECT * FROM owner.users WHERE login = ?;";
        String sql_roles = "SELECT user_id, role_id, owner.roles.name FROM owner.user_roles LEFT OUTER JOIN owner.roles on owner.roles.id = user_roles.role_id LEFT OUTER JOIN owner.users on owner.users.id = user_roles.user_id where login=?;";
        try (Connection conn = ConnectionPool.getConnect();
             PreparedStatement firstPreparedStatement = conn.prepareStatement(sql_users);
             PreparedStatement secondPreparedStatement = conn.prepareStatement(sql_roles)) {
            firstPreparedStatement.setString(1, login);
            secondPreparedStatement.setString(1, login);

            ResultSet rs_users = firstPreparedStatement.executeQuery();
            ResultSet rs_roles = secondPreparedStatement.executeQuery();

            if (rs_users.next()) {
                usr = new User(
                        rs_users.getInt("id"),
                        rs_users.getString("name"),
                        rs_users.getString("login"),
                        rs_users.getString("password"),
                        rs_users.getDate("dateOfBirth"),
                        rs_users.getInt("age"),
                        rs_users.getBigDecimal("salary")
                );
                ArrayList<Role> roles = new ArrayList<>();
                while (rs_roles.next()) {
                    role = new Role(
                            rs_roles.getInt("role_id"),
                            rs_roles.getString("name")
                    );
                    roles.add(role);
                }
                usr.setRoles(roles);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usr;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        User usr;
        ArrayList<User> users = new ArrayList<>();
        Role role;

        String sql_roles = "SELECT user_id, role_id, owner.roles.name FROM owner.user_roles LEFT OUTER JOIN owner.roles on owner.roles.id = user_roles.role_id LEFT OUTER JOIN owner.users on owner.users.id = user_roles.user_id where user_id=?;";
        try (Connection conn = ConnectionPool.getConnect();
             Statement stmt = conn.createStatement();
             PreparedStatement preparedStatement = conn.prepareStatement(sql_roles)) {

            //выборка всех данных по пользователю
            String sql_users = "SELECT * FROM owner.users;";
            ResultSet rs_users = stmt.executeQuery(sql_users);

            while (rs_users.next()) {

                usr = new User(
                        rs_users.getInt("id"),
                        rs_users.getString("name"),
                        rs_users.getString("login"),
                        rs_users.getString("password"),
                        rs_users.getDate("dateOfBirth"),
                        rs_users.getInt("age"),
                        rs_users.getBigDecimal("salary")
                );

                preparedStatement.setInt(1, usr.getId());
                ResultSet rs_roles = preparedStatement.executeQuery();
                ArrayList<Role> roles = new ArrayList<>();
                while (rs_roles.next()) {
                    role = new Role(
                            rs_roles.getInt("role_id"),
                            rs_roles.getString("name")
                    );
                    roles.add(role);
                }
                usr.setRoles(roles);

                users.add(usr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}