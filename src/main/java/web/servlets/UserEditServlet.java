package web.servlets;

import dao.UserDaoImpl;
import domain.Role;
import domain.User;
import service.UserServiceSingleton;
import utilities.FieldsValidation;

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

@WebServlet(name = "UserEditServlet", value = "/useredit.jhtml")
public class UserEditServlet extends HttpServlet {
    String loginToEdit;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loginToEdit = req.getParameter("usertoedit");
        HttpSession session = req.getSession();
        UserDaoImpl userDao = new UserDaoImpl();
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
        ServletContext servletContext = getServletContext();

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        System.out.println("email"+email);
        String dateOfBirth = req.getParameter("dateOfBirth");
        System.out.println(dateOfBirth);

        FieldsValidation fieldsValidation = new FieldsValidation();

        boolean emptySalary = fieldsValidation.isEmptySalary(req.getParameter("salary"));
        BigDecimal salary = fieldsValidation.setSalary(emptySalary,session,req.getParameter("salary"));

        boolean emptyRoles = fieldsValidation.isEmptyRole(req.getParameterValues("roleChoice"));
        ArrayList<Role> roles = fieldsValidation.setRoles(emptyRoles,session,req.getParameterValues("roleChoice"));

        String errString = fieldsValidation.editUserCheck(servletContext,password,salary,email,dateOfBirth);
        String emptyString = fieldsValidation.checkEmpty(servletContext,name,login,password,email,salary,dateOfBirth,roles);

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        if(!dateOfBirth.isEmpty()){
        try {
            date = sf.parse(dateOfBirth);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        }

        User user= new User(name,login,password,date, email,salary);
        user.setRoles(roles);

        if (errString.isEmpty() && emptyString.isEmpty()) {
            session.setAttribute("message", "Пользователь " + login + " отредактирован.");
            UserServiceSingleton.getInstance().getValue().editUser(name, login, password, dateOfBirth, email, salary, roles);
            resp.sendRedirect(req.getContextPath() + "/users.jhtml");
        } else {
            session.setAttribute("editUser",user);
            req.getRequestDispatcher("/jsp/user_edit.jsp").forward(req, resp);
        }
    }
}
