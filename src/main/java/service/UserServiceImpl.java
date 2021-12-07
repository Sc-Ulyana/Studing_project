package service;

//import dao.MemoryUserDAOImpl;
import dao.SqlUserDAO;
import dao.SqlUserDaoImpl;
import dao.UserDAO;
import domain.Role;
import domain.User;
import utilities.ConnectionPool;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserServiceImpl implements UserService{
    private SqlUserDAO sqlUserDao = new SqlUserDaoImpl();


    @Override
    public boolean addUser(String name, String login, String password, String dateOfBirth, int age, BigDecimal salary, ArrayList<Role> roles) {
        if(sqlUserDao.getUser(login)==null){
            sqlUserDao.addUser(name,login,password,dateOfBirth,age,salary,roles);
        return true;
        }else{
        return false;
        }
    }

    @Override
    public boolean editPassword(String login, String oldPassword, String newPassword, String newPasswordRepeat) {
        String sql = "UPDATE owner.users SET password =? where login = ?";
        boolean result=false;
        try(Connection conn = ConnectionPool.getConnect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(2,login);
            for (User user : sqlUserDao.getAllUsers()) {
                if (user.getLogin().equals(login)) {
                    if (user.getPassword().equals(oldPassword)) {
                        if (newPassword.equals(newPasswordRepeat)) {
                            preparedStatement.setString(1,newPasswordRepeat);
                            preparedStatement.executeUpdate();
                            result = true;
                        }
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean checkUser(String login, String password) {
        for(User user : sqlUserDao.getAllUsers()){
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
        return sqlUserDao.getUser(login);
    }

    @Override
    public void deleteUser(String login) {
        sqlUserDao.deleteUser(login);
    }

    @Override
    public boolean editUser(String name, String login, String password, String dateOfBirth, int age, BigDecimal salary, ArrayList<Role> roles) {
        sqlUserDao.editUser(name,login,password,dateOfBirth,age,salary,roles);
        return true;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return sqlUserDao.getAllUsers();
    }
}
