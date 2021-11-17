package servlets;

import classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserEditServlet", value = "/userdelete.jhtml")
public class UserDeleteServlet extends HttpServlet {
    String loginForDelete;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loginForDelete = req.getParameter("usertodelete");
        HttpSession session = req.getSession();
        ArrayList<User> users = (ArrayList<User>) session.getAttribute("usersList");
        for (User u : users) {
            if (u.getLogin().equals(loginForDelete)) {
                session.setAttribute("deleteUser", u);
                break;
            }
        }
        req.getRequestDispatcher("jsp/user_delete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        ArrayList<User> users = (ArrayList<User>) session.getAttribute("usersList");

        for (User u : users) {
            if (u.getLogin().equals(loginForDelete)) {
                users.remove(u);
                break;
            }
        }
        session.setAttribute("usersList", users);
        resp.sendRedirect(req.getContextPath() + "/users.jhtml");
    }
}
