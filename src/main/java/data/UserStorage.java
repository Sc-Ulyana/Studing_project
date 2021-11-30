package data;

import domain.User;

import java.util.ArrayList;

public class UserStorage {
    public static ArrayList<User> users;

    public static void userStorageInit( ){
        users = new ArrayList<User>();
        users.add(new User("Admin", "Admin", "admin", "admin", "admin@email.com","1995-11-11", "ROLE_ADMIN"));
        users.add(new User("Petya", "Petrov", "user", "user", "petya@email.com","2000-08-08", "ROLE_USER"));
        users.add(new User("Vasya", "Vasil'ev", "vasya", "123", "vasya@email.com","1990-07-07", "ROLE_USER"));
    }

    public static ArrayList<User> getUser(){
        return users;
    }
}
