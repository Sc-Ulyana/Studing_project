package web.servlets;

import dao.MemoryUserDAOImpl;
import data.UserStorage;
import domain.User;
import service.UserServiceSingleton;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "LoginServlet", value = "/login.jhtml")
public class LoginServlet extends HttpServlet {

     ArrayList<User> users;

    public void init() throws ServletException{
        UserStorage.userStorageInit();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        req.setAttribute("login",login);
        req.setAttribute("password",password);

        MemoryUserDAOImpl test = new MemoryUserDAOImpl();

        if (UserServiceSingleton.getInstance().getValue().checkUser(login,password)) {
            User user = UserServiceSingleton.getInstance().getValue().getUser(login);
            session.setAttribute("user",user);
            session.setAttribute("login",user.getLogin());
            assert user != null;
            session.setAttribute("name",user.getName());
            session.setAttribute("surname",user.getSurname());
            session.setAttribute("role",user.getRole());
            resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
        } else {
            req.setAttribute("error", "Пользователь не найден");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }

    }
}
