package web.servlets;

import domain.Role;
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
import java.math.BigDecimal;
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
        String emptySalary = "";
        String emptyAge = "";
        String rolesErr = "Выберите хотя бы одну роль.";
        String emptyRoles = "";
        String fieldEmpty = "Поле обязательно для заполнения";
        int age = 0;
        BigDecimal salary = null;
        ArrayList<Role> roles = new ArrayList<>();

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (req.getParameter("age").equals("")) {
            session.setAttribute("emptyAge", fieldEmpty);
            emptyAge = "1";
        } else {
            age = Integer.parseInt(req.getParameter("age"));
        }
        String dateOfBirth = req.getParameter("dateOfBirth");

        if (req.getParameter("salary").equals("")) {
            session.setAttribute("emptySalary", fieldEmpty);
            emptySalary = "1";
        } else {
            salary = new BigDecimal(req.getParameter("salary"));
        }

        if (req.getParameterValues("roleChoice") == null) {
            req.setAttribute("emptyRoles", rolesErr);
            emptyRoles = "1";
        } else {
            String[] rolesChoice = req.getParameterValues("roleChoice");
            for (String role : rolesChoice) {
                roles.add(UserServiceSingleton.getInstance().getValue().getRoleIdByRoleName(role));
            }
        }

            FieldsValidation fieldsValidation = new FieldsValidation();
            String checkLogin = fieldsValidation.checkLogin(login);
            String checkPassword = fieldsValidation.checkPassword(password);
            String checkDate = fieldsValidation.checkDate(dateOfBirth);
            String checkAge = fieldsValidation.checkAge(age);
            String checkSalary = fieldsValidation.checkSalary(salary);
            String checkEmpty = fieldsValidation.checkEmpty(name, login, password, dateOfBirth);

            String errString = checkLogin + checkPassword + checkDate + checkAge + checkSalary + checkEmpty + emptyRoles + emptySalary + emptyAge;

            if (errString.equals("")) {
                session.setAttribute("message", "Пользователь " + login + " добавлен.");
                UserServiceSingleton.getInstance().getValue().addUser(name, login, password, dateOfBirth, age, salary, roles);
                resp.sendRedirect(req.getContextPath() + "/users.jhtml");
            } else {
                req.setAttribute("checkLogin", checkLogin);
                req.setAttribute("checkPassword", checkPassword);
                req.setAttribute("checkDate", checkDate);
                req.setAttribute("checkAge", checkAge);
                req.setAttribute("checkSalary", checkSalary);
                req.setAttribute("checkEmpty", checkEmpty);
                req.getRequestDispatcher("/jsp/user_add.jsp").forward(req, resp);
            }
        }
    }
