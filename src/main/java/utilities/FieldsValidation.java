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

    public static boolean isEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isEmpty(String name, String surname, String login, String password, String email, String dateOfBirth) {
        if (name.isEmpty() || surname.isEmpty() || login.isEmpty() || password.isEmpty() || email.isEmpty() || dateOfBirth.isEmpty()) {
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

    public String checkEmail(String email) {
        String emailMessage = "";
        if (!isEmail(email)) {
            emailMessage = "Неверный формат email.";
        }
        return emailMessage;
    }

    public String checkEmpty(String name, String surname, String login, String password, String email, String dateOfBirth) {
        String emptyMessage = "";
        if (!isEmpty(name, surname, login, password, email, dateOfBirth)) {
            emptyMessage = "Поле обязательно для заполнения.";
        }
        return emptyMessage;
    }
}

