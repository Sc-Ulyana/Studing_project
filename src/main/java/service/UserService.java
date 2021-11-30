package service;

import domain.User;

import java.util.ArrayList;

public interface UserService {
    boolean addUser(String name, String surname, String login, String password, String email, String dateOfBirth, String role);

    boolean editPassword(String login, String oldPassword, String newPassword, String newPasswordRepeat);

    boolean checkUser(String login, String password);

    User getUser(String login);

    void deleteUser(String login);

    boolean editUser(String name, String surname, String login, String password, String email, String dateOfBirth, String role);

    ArrayList<User> getAllUsers();
}
