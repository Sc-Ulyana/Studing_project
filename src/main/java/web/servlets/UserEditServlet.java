package web.servlets;

import dao.SqlUserDaoImpl;
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
import java.util.Arrays;

@WebServlet(name = "UserEditServlet", value = "/useredit.jhtml")
public class UserEditServlet extends HttpServlet {

    String loginToEdit;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loginToEdit = req.getParameter("usertoedit");
        HttpSession session = req.getSession();
        ArrayList<User> users = (ArrayList<User>) session.getAttribute("usersList");
        SqlUserDaoImpl userDao = new SqlUserDaoImpl();
        for (User u : userDao.getAllUsers()) {
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
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int age = Integer.valueOf(req.getParameter("age"));
        String dateOfBirth = req.getParameter("dateOfBirth");
        BigDecimal salary = BigDecimal.valueOf(Long.parseLong(req.getParameter("salary")));

        String[] rolesChoice = req.getParameterValues("roleChoice");
        ArrayList<Role> roles = new ArrayList<>();
        for(String role:rolesChoice){
            roles.add(UserServiceSingleton.getInstance().getValue().getRoleIdByRoleName(role));
        }

        FieldsValidation fieldsValidation = new FieldsValidation();
        String checkLogin = fieldsValidation.checkLogin(login);
        String checkPassword = fieldsValidation.checkPassword(password);
        String checkDate = fieldsValidation.checkDate(dateOfBirth);
//        String checkEmail = fieldsValidation.checkEmail(email);
//        String checkEmpty = fieldsValidation.checkEmpty(name/*,surname*/,login,password/*,email*/,dateOfBirth);

        //      String errString = checkLogin + checkPassword + checkDate + checkEmail + checkEmpty;

//        if (errString.equals("Пользователь с таким именем уже существует.")) {
        UserServiceSingleton.getInstance().getValue().editUser(name, login, password, dateOfBirth, age, salary, roles);
        req.setAttribute("usersList", UserServiceSingleton.getInstance().getValue().getAllUsers());
        session.setAttribute("message", "Пользователь " + login + " отредактирован.");
        resp.sendRedirect(req.getContextPath() + "/users.jhtml");
//        } else {
//            req.setAttribute("checkLogin", checkLogin);
//            req.setAttribute("checkPassword", checkPassword);
//            req.setAttribute("checkDate", checkDate);
//            req.setAttribute("checkEmail",checkEmail);
//            req.setAttribute("checkEmpty",checkEmpty);
//            req.getRequestDispatcher("/jsp/user_edit.jsp").forward(req, resp);
//        }
//    }
    }
}

