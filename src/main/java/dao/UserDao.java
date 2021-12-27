package dao;

import domain.Role;
import domain.User;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface UserDao {
    void addUser(
            String name,
            String login,
            String password,
            String dateOfBirth,
            String email,
            BigDecimal salary,
            ArrayList<Role> roles
    );

    void deleteUser(String login);

    void editUser(String name, String login, String password, String dateOfBirth, String email, BigDecimal salary, ArrayList<Role> roles);

    User getUser(String login);

    ArrayList<User> getAllUsers();
}
