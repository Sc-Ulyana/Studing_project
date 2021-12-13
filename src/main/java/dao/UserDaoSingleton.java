package dao;

public class UserDaoSingleton {
    public UserDao value;
    private static UserDaoSingleton instance;

    private UserDaoSingleton() {
        this.value = new UserDaoImpl();
    }

    public static UserDaoSingleton getInstance() {
        if (instance == null) {
            instance = new UserDaoSingleton();
        }
        return instance;
    }

    public UserDao getValue() {
        return value;
    }
}
