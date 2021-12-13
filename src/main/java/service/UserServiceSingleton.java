package service;

public class UserServiceSingleton {
    public UserService value;
    private static UserServiceSingleton instance;

    private UserServiceSingleton(){
        this.value = new UserServiceImpl();
    }

    public static UserServiceSingleton getInstance() {
        if (instance == null) {
            instance = new UserServiceSingleton();
        }
        return instance;
    }

    public UserService getValue() {
        return value;
    }
}
