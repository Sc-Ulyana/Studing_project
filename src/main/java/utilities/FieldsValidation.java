package utilities;

import domain.User;
import service.UserServiceSingleton;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    public static boolean isAge(Integer age) {
        return age >= 18 && age <= 120;
    }

    public static boolean isSalary(BigDecimal salary) {
        if (salary != null) {
            int intSalary = Integer.parseInt(salary.toString());
            return intSalary >= 1000 && intSalary <= 20000;
        }else {
            return false;
        }
    }

    public static boolean isEmpty(String name, String login, String password, String dateOfBirth) {
        return !name.isEmpty() && !login.isEmpty() && !password.isEmpty() && !dateOfBirth.isEmpty();
    }

    public String checkLogin(String login) {
        String loginMessage = "";
        if (!isUniqueLogin(login)) {
            loginMessage = "Пользователь с таким именем уже существует.";
        }
        return loginMessage;
    }

    public String checkPassword(String password) {
        String passMessage = "";
        if (!isPassword(password) && !password.equals("")) {
            passMessage = "Слишком короткий пароль.";
        }
        return passMessage;
    }

    public String checkDate(String date) {
        String dateMessage = "";
        if (!isDate(date) && !date.equals("")) {
            dateMessage = "Неверный формат даты.";
        }
        return dateMessage;
    }

    public String checkAge(Integer age) {
        String ageMessage = "";
        if (!isAge(age) && age.toString() != null) {
            ageMessage = "Возрастной диапазон пользователя 18-120";
        }
        return ageMessage;
    }

    public String checkSalary(BigDecimal salary) {
        String salaryMessage = "";
        if (!isSalary(salary) && salary != null) {
            salaryMessage = "Диапазон заработной платы 1000-20000";
        }
        return salaryMessage;
    }

    public String checkEmpty(String name, String login, String password, String dateOfBirth) {
        String emptyMessage = "";
        if (!isEmpty(name, login, password, dateOfBirth)) {
            emptyMessage = "Поле обязательно для заполнения.";
        }
        return emptyMessage;
    }
}

