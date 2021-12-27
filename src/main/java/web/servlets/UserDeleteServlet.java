package web.servlets;

import service.UserServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserDeleteServlet", value = "/userdelete.jhtml")
public class UserDeleteServlet extends HttpServlet {
    String login;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login = req.getParameter("usertodelete");
        req.setAttribute("usertodelete", login);
        req.getRequestDispatcher("jsp/user_delete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceSingleton.getInstance().getValue().deleteUser(login);
        resp.sendRedirect(req.getContextPath() + "/users.jhtml");
    }
}
