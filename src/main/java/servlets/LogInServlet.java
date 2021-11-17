package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import classes.*;

@WebServlet(name = "LoginServlet", value = "/login.jhtml")
public class LogInServlet extends HttpServlet {
    ArrayList<User> users;

    @Override
    public void init() throws ServletException {
        users = new ArrayList<>();
        users.add(new User("Admin", "Admin", "admin", "admin", "admin@email.com","1995-11-11", "ROLE_ADMIN"));
        users.add(new User("Petya", "Petrov", "user", "user", "petya@email.com","2000-08-08", "ROLE_USER"));
        users.add(new User("Vasya", "Vasil'ev", "vasya", "123", "vasya@email.com","1990-07-07", "ROLE_USER"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        boolean isUser = false;

        HttpSession session = req.getSession();
        req.setAttribute("login",login);
        req.setAttribute("password",password);

        for (User user : users) {
            if (user.getLogin().equals(login)) {
                if (user.getPassword().equals(password)) {
                    isUser = true;
                    session.setAttribute("user", user);
                    session.setAttribute("name", user.getName());
                    session.setAttribute("surname", user.getSurname());
                    session.setAttribute("login", user.getLogin());
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("dateOfBirth", user.getDateOfBirth());
                    session.setAttribute("role", user.getRole());
                    session.setAttribute("usersList", users);
                }
            }
        }

        if (isUser) {
            resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
        } else {
            req.setAttribute("error", "Пользователь не найден");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }

    }
}
