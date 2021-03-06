package utilities;

import domain.User;
import domain.Role;
import service.UserServiceSingleton;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

    public static boolean isSalary(BigDecimal salary) {
        if (salary != null) {
            int intSalary = Integer.parseInt(salary.toString());
            return intSalary >= 1000 && intSalary <= 20000;
        } else {
            return false;
        }
    }

    public static boolean isEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isEmpty(String name, String login, String password, String dateOfBirth, String email) {
        return !name.isEmpty() && !login.isEmpty() && !password.isEmpty() && !dateOfBirth.isEmpty() && !email.isEmpty();
    }

    public boolean isEmptySalary(String salary) {
        return salary.isEmpty();
    }

    public boolean isEmptyRole(String[] roleChoice) {
        return roleChoice == null;
    }


    public BigDecimal setSalary(boolean result, HttpSession session, String someString) {
        BigDecimal salary = null;
        if (result == true) {
            session.setAttribute("emptySalary", "???????? ?????????????????????? ?????? ????????????????????");
            return salary;
        } else {
            salary = new BigDecimal(someString);
            return salary;
        }
    }

    public ArrayList<Role> setRoles(boolean result, HttpSession session, String[] someString) {
        ArrayList<Role> roles = new ArrayList<>();
        if (result) {
            session.setAttribute("emptyRoles", "???????????????? ???????? ???? ???????? ????????.");
            return roles;
        } else {
            for (String role : someString) {
                roles.add(UserServiceSingleton.getInstance().getValue().getRoleIdByRoleName(role));
            }
            return roles;
        }
    }

    public String checkLogin(String login) {
        String loginMessage = "";
        if (!isUniqueLogin(login)) {
            loginMessage = "???????????????????????? ?? ?????????? ???????????? ?????? ????????????????????.";
        }
        return loginMessage;
    }

    public String checkPassword(String password) {
        String passMessage = "";
        if (!isPassword(password) && !password.equals("")) {
            passMessage = "?????????????? ???????????????? ????????????.";
        }
        return passMessage;
    }

    public String checkDate(String date) {
        String dateMessage = "";
        if (!isDate(date) && !date.equals("")) {
            dateMessage = "???????????????? ???????????? ????????.";
        }
        return dateMessage;
    }

    public String checkSalary(BigDecimal salary) {
        String salaryMessage = "";
        if (!isSalary(salary) && salary != null) {
            salaryMessage = "???????????????? ???????????????????? ?????????? 1000-20000";
        }
        return salaryMessage;
    }

    public String checkEmail(String email) {
        String emailMessage = "";
        if (!isEmail(email)) {
            emailMessage = "???????????????? ???????????? email.";
        }
        return emailMessage;
    }

    public String checkEmpty(String name, String login, String password, String dateOfBirth, String email) {
        String emptyMessage = "";
        if (!isEmpty(name, login, password, dateOfBirth, email)) {
            emptyMessage = "???????? ?????????????????????? ?????? ????????????????????.";
        }
        return emptyMessage;
    }

    public String editUserCheck(ServletContext selvletContext, String password, BigDecimal salary, String email, String date) {
        String checkSalary = checkSalary(salary);
        String checkEmail = checkEmail(email);
        String checkPassword = checkPassword(password);
        String checkDate = checkDate(date);
        selvletContext.setAttribute("checkSalary", checkSalary);
        selvletContext.setAttribute("checkEmail", checkEmail);
        selvletContext.setAttribute("checkPassword", checkPassword);
        selvletContext.setAttribute("checkDate", checkDate);
        String errMassage = checkEmail + checkDate + checkSalary + checkPassword;
        return errMassage;
    }

    public String addUserCheck(ServletContext servletContext, String login, String password, BigDecimal salary,String email, String date) {
        String checkSalary = checkSalary(salary);
        String checkEmail = checkEmail(email);
        String checkPassword = checkPassword(password);
        String checkDate = checkDate(date);
        String checkLogin = checkLogin(login);
        servletContext.setAttribute("checkLogin", checkLogin);
        servletContext.setAttribute("checkSalary", checkSalary);
        servletContext.setAttribute("checkEmail", checkEmail);
        servletContext.setAttribute("checkPassword", checkPassword);
        servletContext.setAttribute("checkDate", checkDate);
        String errMassage = checkLogin + checkEmail + checkDate + checkSalary + checkPassword;
        return errMassage;
    }

    public String checkEmpty(ServletContext servletContext, String name, String login, String password, String email, BigDecimal salary, String date, ArrayList<Role> roles) {
        String emptyString = "???????? ?????????????????????? ?????? ????????????????????";
        String emptyRoles = "???????????????? ????????";
        String errString = "";
        if (name.isEmpty()) {
            servletContext.setAttribute("emptyName", emptyString);
            errString += "name";
        }
        if (login.isEmpty()) {
            servletContext.setAttribute("emptyLogin", emptyString);
            errString += "login";
        }
        if (password.isEmpty()) {
            servletContext.setAttribute("emptyPassword", emptyString);
            errString += "password";
        }
        if (email.isEmpty()) {
            servletContext.setAttribute("emptyEmail", emptyString);
            errString += "age";
        }
        if (salary == null) {
            servletContext.setAttribute("emptySalary", emptyString);
            errString += "salary";
        }
        if (date.isEmpty()) {
            servletContext.setAttribute("emptyDate", emptyString);
            errString += "date";
        }
        if (roles.toString().equals("[]")) {
            servletContext.setAttribute("emptyRoles", emptyRoles);
            errString += "roles";
        }
        return errString;
    }
}

