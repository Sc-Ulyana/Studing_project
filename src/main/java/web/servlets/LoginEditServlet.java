package web.servlets;

import domain.User;
import service.UserService;
import service.UserServiceSingleton;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "LoginEditServlet", value = "/loginedit.jhtml")
public class LoginEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login_edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String newPasswordRepeat = req.getParameter("newPasswordRepeat");

        HttpSession session = req.getSession();

        String login = (String) session.getAttribute("login");
        if (UserServiceSingleton.getInstance().getValue().editPassword(login,oldPassword,newPassword,newPasswordRepeat)) {
            req.setAttribute("message", "Пароль успешно сменен");
            req.getRequestDispatcher("/jsp/welcome.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Неверный пароль");
            req.getRequestDispatcher("/jsp/login_edit.jsp").forward(req, resp);
        }
    }
}