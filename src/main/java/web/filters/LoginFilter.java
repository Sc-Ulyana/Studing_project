package web.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", value = "/login.jhtml")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/welcome.jhtml");
        } else {
            chain.doFilter(request, response);
        }
    }
}

