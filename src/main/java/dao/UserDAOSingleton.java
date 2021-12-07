package dao;

public class UserDAOSingleton {
    public SqlUserDAO value;
    private static UserDAOSingleton instance;

    private UserDAOSingleton() {
        this.value = new SqlUserDaoImpl();
    }

    public static UserDAOSingleton getInstance() {
        if (instance == null) {
            instance = new UserDAOSingleton();
        }
        return instance;
    }

    public SqlUserDAO getValue() {
        return value;
    }
}
