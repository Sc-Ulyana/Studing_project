package dao;

import data.UserStorage;
import domain.User;

import java.util.ArrayList;

public class MemoryUserDAOImpl implements UserDAO {
    @Override
    public void addUser(String name, String surname, String login, String password, String email, String dateOfBirth, String role) {
        UserStorage.users.add(new User(name, surname, login, password, email, dateOfBirth, role));
    }

    @Override
    public void deleteUser(String login) {
        for (User user : UserStorage.users) {
            if (user.getLogin().equals(login)) {
                UserStorage.users.remove(user);
                break;
            }
        }
    }

    @Override
    public void editUser(String name, String surname, String login, String password, String email, String dateOfBirth, String role) {
        for (User user : UserStorage.users) {
            if (user.getLogin().equals(login)) {
                int userIndex = UserStorage.users.indexOf(user);
                UserStorage.users.set(userIndex, new User(name, surname, login, password, email, dateOfBirth, role));
            }
        }
    }

    @Override
    public User getUser(String login) {
        for (User user : UserStorage.users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return UserStorage.users;
    }
}
