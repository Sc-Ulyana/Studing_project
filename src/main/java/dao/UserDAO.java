package dao;

import domain.User;

import java.util.ArrayList;

public interface UserDAO {
    void addUser(String name, String surname, String login, String password, String email, String dateOfBirth, String role);

    void deleteUser(String login);

    void editUser(String name, String surname, String login, String password, String email, String dateOfBirth, String role);

    User getUser(String login);

    ArrayList<User> getAllUsers();
}
