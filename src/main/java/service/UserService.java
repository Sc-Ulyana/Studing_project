package service;

import domain.Role;
import domain.User;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface UserService {
    boolean addUser(String name, String login, String password, String dateOfBirth, int age, BigDecimal salary, ArrayList<Role> roles);

    boolean editPassword(String login, String oldPassword, String newPassword, String newPasswordRepeat);

    boolean checkUser(String login, String password);

    User getUser(String login);

    void deleteUser(String login);

    boolean editUser(String name, String login, String password, String dateOfBirth, int age, BigDecimal salary, ArrayList<Role> roles);

    ArrayList<User> getAllUsers();

    Role getRoleIdByRoleName(String name);
}
