package domain;


import service.UserServiceSingleton;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class User {
    private int id;
    //private String surname;
    private String login;
    private String password;
    private String name;
    //private String email;
    private Date dateOfBirth;
    private int age;
    private BigDecimal salary;
    private ArrayList<Role> roles;

    public User(int id, String name, String login, String password, Date dateOfBirth,int age,BigDecimal salary) {
        this.id = id;
        this.name = name;
      //  this.surname = surname;
        this.login = login;
        this.password = password;
      //  this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.age=age;
        this.salary=salary;
    }
    public String getName() { return name; }

    //   public String getSurname() { return surname; }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

//    public String getEmail() { return email; }

    public Date getDateOfBirth() { return dateOfBirth; }

    public int getAge() {
        return age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setName(String name) { this.name = name; }

    // public void setSurname(String surname) { this.surname = surname; }

    public void setLogin(String login) { this.login = login; }

    public void setPassword(String password) {
        this.password = password;
    }

   // public void setEmail(String email) { this.email = email; }

    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles.toString() + '\'' +
                '}';
    }

    public boolean hasRole(String role) {
        String strRoles = roles.toString();
        if(strRoles.contains(role)){
            System.out.println("true");
        return true;
        }else{
            System.out.println("false");
            return false;
        }
    }
}
