package ua.nure.patsera.periodicals.web.controller.filters;

import ua.nure.patsera.periodicals.bean.User;
import ua.nure.patsera.periodicals.service.UserService;
import ua.nure.patsera.periodicals.web.controller.ServletAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Дарина on 19.09.2017.
 */
@WebFilter
public class CheckRoleFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        execute(req, resp, chain);
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }

    private void execute(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        HttpServletResponse response = (HttpServletResponse) resp;
        String role = (String)session.getAttribute(ServletAttributes.USER_ROLE);
        if ((role == null) || (!role.equals(ServletAttributes.ADMIN))) {
            response.sendRedirect(ServletAttributes.MAIN_PAGE_SERVLET);
        }
    }
}
