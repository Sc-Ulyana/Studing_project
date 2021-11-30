package web.servlets;

import dao.MemoryUserDAOImpl;
import service.UserServiceImpl;
import service.UserServiceSingleton;
import utilities.FieldsValidation;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/useradd.jhtml")
public class UserAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/user_add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String dateOfBirth = req.getParameter("dateOfBirth");
        String role = req.getParameter("role");

        FieldsValidation fieldsValidation = new FieldsValidation();
        String checkLogin = fieldsValidation.checkLogin(login);
        String checkPassword = fieldsValidation.checkPassword(password);
        String checkDate = fieldsValidation.checkDate(dateOfBirth);
        String checkEmail = fieldsValidation.checkEmail(email);
        String checkEmpty = fieldsValidation.checkEmpty(name,surname,login,password,email,dateOfBirth);

        String errString = checkLogin + checkPassword + checkDate + checkEmail + checkEmpty;


        if (errString.equals("")) {
            UserServiceSingleton.getInstance().getValue().addUser(name,surname,login,password,email,dateOfBirth,role);
            session.setAttribute("message", "Пользователь " + login + " добавлен.");
            resp.sendRedirect(req.getContextPath() + "/users.jhtml");
        } else {
            req.setAttribute("checkLogin", checkLogin);
            req.setAttribute("checkPassword", checkPassword);
            req.setAttribute("checkDate", checkDate);
            req.setAttribute("checkEmail",checkEmail);
            req.setAttribute("checkEmpty",checkEmpty);
            req.getRequestDispatcher("/jsp/user_add.jsp").forward(req, resp);
        }
    }
}
