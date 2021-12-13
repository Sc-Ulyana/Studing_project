package web.servlets;

import domain.User;
import service.UserServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login.jhtml")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        req.setAttribute("login", login);
        req.setAttribute("password", password);

        if (UserServiceSingleton.getInstance().getValue().checkUser(login, password)) {
            User user = UserServiceSingleton.getInstance().getValue().getUser(login);
            session.setAttribute("user", user);

            session.setAttribute("login", user.getLogin());
            assert user != null;
            session.setAttribute("name", user.getName());
            session.setAttribute("role", user.getRoles());
            resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
        } else {
            req.setAttribute("errorEntrance", "Пользователь не найден");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }

    }
}
