package web.servlets;

import dao.MemoryUserDAOImpl;
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

        String name = req.getParameter("name");
//        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int age = Integer.valueOf(req.getParameter("age"));
//        String email = req.getParameter("email");
        String dateOfBirth = req.getParameter("dateOfBirth");
        BigDecimal salary = BigDecimal.valueOf(Double.valueOf(req.getParameter("salary")));
        ArrayList<Role> role = null;
        String[] outerArray = req.getParameterValues("role");
        for (int i = 0; i < outerArray.length; i++) {
            String[] innerArray=outerArray[i].split(",");
        }

//        FieldsValidation fieldsValidation = new FieldsValidation();
//        String checkLogin = fieldsValidation.checkLogin(login);
//        String checkPassword = fieldsValidation.checkPassword(password);
//        String checkDate = fieldsValidation.checkDate(dateOfBirth);
//        String checkEmail = fieldsValidation.checkEmail(email);
//        String checkEmpty = fieldsValidation.checkEmpty(name,surname,login,password,email,dateOfBirth);
//
//        String errString = checkLogin + checkPassword + checkDate + checkEmail + checkEmpty;



//        if (errString.equals("")) {
////          session.setAttribute("message", "Пользователь " + login + " добавлен.");
            UserServiceSingleton.getInstance().getValue().addUser(name,login,password,dateOfBirth,age,salary,role);
            resp.sendRedirect(req.getContextPath() + "/users.jhtml");
//        } else {
//            req.setAttribute("checkLogin", checkLogin);
//            req.setAttribute("checkPassword", checkPassword);
//            req.setAttribute("checkDate", checkDate);
//            req.setAttribute("checkEmail",checkEmail);
//            req.setAttribute("checkEmpty",checkEmpty);
//            req.getRequestDispatcher("/jsp/user_add.jsp").forward(req, resp);
//        }
    }
}
