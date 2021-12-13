package web.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "WelcomeFilter", value = "/*")
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        String pathToLoginServlet = ((HttpServletRequest) request).getRequestURI();
        if (session != null && session.getAttribute("user") != null) {
            chain.doFilter(request, response);
        } else {
            if (pathToLoginServlet.equals("/webdipatch/login.jhtml")) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/login.jhtml");
            }
        }
    }
}
