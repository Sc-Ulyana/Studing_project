package service;

import dao.MemoryUserDAOImpl;
import dao.UserDAO;
import domain.User;

import java.util.ArrayList;

public class UserServiceImpl implements UserService{
    private UserDAO userDAO = new MemoryUserDAOImpl();

    @Override
    public boolean addUser(String name, String surname, String login, String password, String email, String dateOfBirth, String role) {
        if(userDAO.getUser(login)==null){
        userDAO.addUser(name,surname,login,password,email,dateOfBirth,role);
        return true;
        }else{
        return false;
        }
    }

    @Override
    public boolean editPassword(String login, String oldPassword, String newPassword, String newPasswordRepeat) {
        for(User user: userDAO.getAllUsers()){
            if(user.getLogin().equals(login)){
                if(user.getPassword().equals(oldPassword)){
                    if(newPassword.equals(newPasswordRepeat)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean checkUser(String login, String password) {
        for(User user : userDAO.getAllUsers()){
            if(user.getLogin().equals(login)){
                if(user.getPassword().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public User getUser(String login) {
        return userDAO.getUser(login);
    }

    @Override
    public void deleteUser(String login) {
        userDAO.deleteUser(login);
    }

    @Override
    public boolean editUser(String name, String surname, String login, String password, String email, String dateOfBirth, String role) {
        userDAO.editUser(name,surname,login,password,email,dateOfBirth,role);
        return true;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
