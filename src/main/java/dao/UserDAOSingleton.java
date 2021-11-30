package dao;

public class UserDAOSingleton {
    public UserDAO value;
    private static UserDAOSingleton instance;

    private UserDAOSingleton() {
        this.value = new MemoryUserDAOImpl();
    }

    public static UserDAOSingleton getInstance() {
        if (instance == null) {
            instance = new UserDAOSingleton();
        }
        return instance;
    }

    public UserDAO getValue() {
        return value;
    }
}
