package web.servlets;

import domain.Role;
import service.UserServiceSingleton;
import utilities.FieldsValidation;
import domain.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/useradd.jhtml")
public class UserAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getAttribute("addUser");
        req.getRequestDispatcher("/jsp/user_add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ServletContext servletContext = getServletContext();

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String dateOfBirth = req.getParameter("dateOfBirth");

        FieldsValidation fieldsValidation = new FieldsValidation();

        boolean emptySalary = fieldsValidation.isEmptySalary(req.getParameter("salary"));
        BigDecimal salary = fieldsValidation.setSalary(emptySalary, session, req.getParameter("salary"));

        boolean emptyRoles = fieldsValidation.isEmptyRole(req.getParameterValues("roleChoice"));
        ArrayList<Role> roles = fieldsValidation.setRoles(emptyRoles, session, req.getParameterValues("roleChoice"));

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sf.parse(dateOfBirth);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = new User(name, login, password, date, email, salary);
        user.setRoles(roles);
        session.setAttribute("addUser", user);

        String errString = fieldsValidation.addUserCheck(servletContext,login,password,salary,email,dateOfBirth);
        String emptyString = fieldsValidation.checkEmpty(servletContext,name,login,password,email,salary,dateOfBirth,roles);

        if (errString.isEmpty() && emptyString.isEmpty()) {
            session.setAttribute("message", "Пользователь " + login + " добавлен.");
            UserServiceSingleton.getInstance().getValue().addUser(name, login, password, dateOfBirth, email, salary, roles);
            resp.sendRedirect(req.getContextPath() + "/users.jhtml");
        } else {
            req.getRequestDispatcher("/jsp/user_add.jsp").forward(req, resp);
        }
    }
}
