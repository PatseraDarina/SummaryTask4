package ua.nure.patsera.periodicals.web.controller;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.User;
import ua.nure.patsera.periodicals.dto.LoginDto;
import ua.nure.patsera.periodicals.exceptions.AuthorizationException;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Дарина on 11.09.2017.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute(ServletAttributes.USER_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        LoginDto loginDto = getLoginDto(request);
       // boolean isExist = true;
        User user;
        try {
            user = userService.login(loginDto);
            session.setAttribute(ServletAttributes.USER, user);
            session.setAttribute(ServletAttributes.USER_ROLE, userService.getUserRole(user.getEmail()));
            Cookie email = new Cookie(ServletAttributes.USER_EMAIL, user.getEmail());
            response.addCookie(email);
            request.getRequestDispatcher(ServletAttributes.FILTER_CHECK_ROLE);
            // response.sendRedirect(ServletAttributes.JSP_PERCONAL_CABINET);
        } catch (AuthorizationException e) {
            session.setAttribute(ServletAttributes.ERROR_MESSAGE, e);
            request.getRequestDispatcher(ServletAttributes.JSP_INDEX).include(request, response);
            LOGGER.warn(e.getMessage());
        } catch (TransactionInterruptedException e) {
            LOGGER.warn(e.getMessage());
            session.setAttribute(ServletAttributes.ERROR_MESSAGE, ServletAttributes.ERROR_SERVER);
            response.sendRedirect(ServletAttributes.JSP_ERROR_PAGE);
        }

    }

    private LoginDto getLoginDto(HttpServletRequest req) {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(req.getParameter(ServletAttributes.USER_EMAIL));
        loginDto.setPassword(req.getParameter(ServletAttributes.USER_PASSWORD));
        return loginDto;
    }
}
