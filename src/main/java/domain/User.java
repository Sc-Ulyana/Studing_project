package domain;

import java.util.Date;
import java.util.Objects;

public class User {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String dateOfBirth;
    private String role;

    public User(String name,String surname, String login, String password, String email, String dateOfBirth, String role) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() { return email; }

    public String getDateOfBirth() { return dateOfBirth; }

    public String getRole() {
        return role;
    }

    public void setName(String name) { this.name = name; }

    public void setSurname(String surname) { this.surname = surname; }

    public void setLogin(String login) { this.login = login; }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) { this.email = email; }

    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public void setRole(String role){this.role = role; }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(login, password, role);
//    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
