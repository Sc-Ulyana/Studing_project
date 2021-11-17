package check;

import classes.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldsValidation {

    public static boolean checkLogin(ArrayList<User> users, String login) {
        for (User usr : users) {
            if (usr.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPassword(String password) {
        if (password.length() < 3) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDate(String date) {
        if (!date.matches("\\d\\d-[0-3]\\d{4}-[01]"))
            return false;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setLenient(false);
        try {
            System.out.println(date + "is correct date");
            df.parse(date);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    public static boolean checkEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        System.out.println("email: " + matcher.matches());
        return matcher.matches();
    }

    public static boolean checkEmpty(String name, String surname, String login, String password, String email, String dateOfBirth) {
        if (name.isEmpty() || surname.isEmpty() || login.isEmpty() || password.isEmpty() || email.isEmpty() || dateOfBirth.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
