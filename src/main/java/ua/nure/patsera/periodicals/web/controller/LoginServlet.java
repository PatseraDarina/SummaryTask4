package ua.nure.patsera.periodicals.web.controller;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.User;
import ua.nure.patsera.periodicals.dto.LoginDto;
import ua.nure.patsera.periodicals.dto.PeriodicalDto;
import ua.nure.patsera.periodicals.exceptions.AuthorizationException;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.service.PeriodicalService;
import ua.nure.patsera.periodicals.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by Дарина on 11.09.2017.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private UserService userService;
    private PeriodicalService periodicalService;


    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute(ServletAttributes.USER_SERVICE);
        periodicalService = (PeriodicalService) getServletContext().getAttribute(ServletAttributes.PERIODICAL_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        LoginDto loginDto = getLoginDto(request);
        List<PeriodicalDto> subscriptionList;
        User user;
        try {
            user = userService.login(loginDto);
            if (user.isBlocked()) {
                session.setAttribute(ServletAttributes.ERROR_MESSAGE, ServletAttributes.ERROR_BLOCKED_UDER);
                response.sendRedirect(ServletAttributes.MAIN_PAGE_SERVLET);
            } else {
                subscriptionList = periodicalService.getPeriodicalDtoByReader(user.getId());
                session.setAttribute(ServletAttributes.SUBSCRIPTION_LIST, subscriptionList);
                session.setAttribute(ServletAttributes.USER, user);
                session.setAttribute(ServletAttributes.USER_ROLE, userService.getUserRole(user.getEmail()));
                response.sendRedirect(ServletAttributes.MAIN_PAGE_SERVLET);
            }
        } catch (AuthorizationException e) {
            session.setAttribute(ServletAttributes.ERROR_MESSAGE, e.getMessage());
            LOGGER.warn(e.getMessage());
        } catch (TransactionInterruptedException e) {
            LOGGER.warn(e.getMessage());
            session.setAttribute(ServletAttributes.ERROR_MESSAGE, e.getMessage());
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
