package utilities;

import domain.User;
import service.UserServiceSingleton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldsValidation {

    public static boolean isUniqueLogin(String login) {
        for (User u : UserServiceSingleton.getInstance().getValue().getAllUsers()) {
            if (u.getLogin().equals(login)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPassword(String password) {
        return password.length() >= 3;
    }

    public static boolean isDate(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(date);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }


    public static boolean isEmpty(String name, String login, String password, String dateOfBirth) {
        if (name.isEmpty() || login.isEmpty() || password.isEmpty() || dateOfBirth.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public String checkLogin(String login){
        String loginMessage = "";
        if(!isUniqueLogin(login)){
            loginMessage = "Пользователь с таким именем уже существует.";
        }
        return loginMessage;
    }

    public String checkPassword(String password) {
        String passMessage = "";
        if (!isPassword(password)) {
            passMessage = "Слишком короткий пароль.";
        }
        return passMessage;
    }

    public String checkDate(String date) {
        String dateMessage = "";
        if (!isDate(date)) {
            dateMessage = "Неверный формат даты.";
        }
        return dateMessage;
    }

    public String checkEmpty(String name, String login, String password, String dateOfBirth) {
        String emptyMessage = "";
        if (!isEmpty(name, login, password, dateOfBirth)) {
            emptyMessage = "Поле обязательно для заполнения.";
        }
        return emptyMessage;
    }
}

