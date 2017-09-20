package ua.nure.patsera.periodicals.web.controller.filters;

import ua.nure.patsera.periodicals.bean.User;
import ua.nure.patsera.periodicals.service.UserService;
import ua.nure.patsera.periodicals.web.controller.ServletAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        User user = (User) request.getSession().getAttribute(ServletAttributes.USER);
        if (user == null) {
            response.sendRedirect(ServletAttributes.JSP_INDEX);
        } else {
            switch ((String) request.getSession().getAttribute(ServletAttributes.USER_ROLE)) {
                case ServletAttributes.ADMIN :
                    response.sendRedirect(ServletAttributes.JSP_ADD_PERIODICALS);
                    break;
                case ServletAttributes.READER :
                    response.sendRedirect(ServletAttributes.JSP_PERCONAL_CABINET);
                    break;
            }
           chain.doFilter(req, resp);
        }
    }
}
