package factory;

import dao.MemoryUserDAOImpl;
import dao.UserDAO;
import domain.User;
import service.UserService;
import service.UserServiceImpl;

import java.sql.Connection;
import java.util.ArrayList;

public class MainServiceFactoryImpl implements  ServiceFactory{
    Connection connection;
    
    @Override
    public UserService createUserService() throws FactoryException {
        UserService userService = new UserServiceImpl();
        return userService;
    }

    @Override
    public UserDAO createUserDAO() throws FactoryException {
        UserDAO userDAO = new MemoryUserDAOImpl();
        return userDAO;
    }

    public void close(){
        try {
            connection = null;
        }catch(Exception e){

        }
    }
}
