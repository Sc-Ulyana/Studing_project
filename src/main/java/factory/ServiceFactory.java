package factory;

import dao.UserDAO;
import service.UserService;

public interface ServiceFactory extends AutoCloseable {
    UserService createUserService() throws FactoryException;

    UserDAO createUserDAO() throws FactoryException;
}
