package servlets;

import check.FieldsValidation;
import classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/useredit.jhtml")
public class UserEditServlet extends HttpServlet {

    String loginToEdit;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loginToEdit = req.getParameter("usertoedit");
        HttpSession session = req.getSession();
        ArrayList<User> users = (ArrayList<User>) session.getAttribute("usersList");
        for (User u : users) {
            if (u.getLogin().equals(loginToEdit)) {
                session.setAttribute("editUser", u);
                break;
            }
        }
        req.getRequestDispatcher("jsp/user_edit.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ArrayList<User> users = (ArrayList<User>) session.getAttribute("usersList");

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String dateOfBirth = req.getParameter("dateOfBirth");
        String role = req.getParameter("role");

        System.out.println(loginToEdit);

        for (User u : users) {
            if (u.getLogin().equals(loginToEdit)) {
                int userIndex = users.indexOf(u);
                users.set(userIndex, new User(name, surname, login, password, email, dateOfBirth, role));
                session.setAttribute("usersList", users);
                break;
            }
        }
        if(FieldsValidation.checkDate(dateOfBirth)||!FieldsValidation.checkEmail(email)||FieldsValidation.checkPassword(password)||FieldsValidation.checkEmpty(name, surname, login, password, email, dateOfBirth)){
        if (FieldsValidation.checkDate(dateOfBirth)) {
            session.setAttribute("checkDate", "Неправильный формат даты");
        } else if (!FieldsValidation.checkEmail(email)) {
            session.setAttribute("checkEmail", "Неправильный формат email");
        } else if (FieldsValidation.checkPassword(password)) {
            session.setAttribute("checkPassword", "Пороль должен содержать не менее 3 символов");
        } else if (FieldsValidation.checkEmpty(name, surname, login, password, email, dateOfBirth)) {
            session.setAttribute("checkEmpty", "Поле не может быть пустым");
        }
            req.getRequestDispatcher("jsp/user_edit.jsp").forward(req, resp);
        }else {
            session.setAttribute("message", "Пользователь " + login + " отредактирован");
            resp.sendRedirect(req.getContextPath() + "/users.jhtml");
        }
    }
}

