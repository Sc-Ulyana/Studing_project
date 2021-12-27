package service;

import dao.UserDao;
import dao.UserDaoImpl;
import domain.Role;
import domain.User;
import utilities.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean addUser(String name, String login, String password, String dateOfBirth, String email, BigDecimal salary, ArrayList<Role> roles) {
        if (userDao.getUser(login) == null) {
            userDao.addUser(name, login, password, dateOfBirth, email, salary, roles);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean editPassword(String login, String oldPassword, String newPassword, String newPasswordRepeat) {
        String sql = "UPDATE project.users SET password =? where login = ?";
        boolean result = false;
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(2, login);
            for (User user : userDao.getAllUsers()) {
                if (user.getLogin().equals(login)) {
                    if (user.getPassword().equals(oldPassword)) {
                        if (newPassword.equals(newPasswordRepeat)) {
                            preparedStatement.setString(1, newPasswordRepeat);
                            preparedStatement.executeUpdate();
                            result = true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean checkUser(String login, String password) {
        for (User user : userDao.getAllUsers()) {
            if (user.getLogin().equals(login)) {
                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public User getUser(String login) {
        return userDao.getUser(login);
    }

    @Override
    public void deleteUser(String login) {
        userDao.deleteUser(login);
    }

    @Override
    public boolean editUser(String name, String login, String password, String dateOfBirth, String email, BigDecimal salary, ArrayList<Role> roles) {
        userDao.editUser(name, login, password, dateOfBirth, email, salary, roles);
        return true;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public Role getRoleIdByRoleName(String name) {
        Role role = null;
        String sql = "SELECT * FROM project.roles WHERE name = ?";
        try(Connection connection = DBConnection.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,name);
            ResultSet rs_roles = preparedStatement.executeQuery();
            if(rs_roles.next()){
                role = new Role(
                        rs_roles.getInt("id"),
                        rs_roles.getString("name")
                        );
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return role;
    }
}
