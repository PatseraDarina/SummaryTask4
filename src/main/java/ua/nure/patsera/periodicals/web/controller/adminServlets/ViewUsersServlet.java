package ua.nure.patsera.periodicals.web.controller.adminServlets;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.Category;
import ua.nure.patsera.periodicals.dto.RegistrationDto;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.service.CategoryService;
import ua.nure.patsera.periodicals.service.PeriodicalService;
import ua.nure.patsera.periodicals.service.UserService;
import ua.nure.patsera.periodicals.web.controller.ServletAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Дарина on 24.09.2017.
 */
@WebServlet
public class ViewUsersServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ViewPeriodicalsServlet.class);
    private UserService userService;


    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute(ServletAttributes.USER_SERVICE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<RegistrationDto> usersList = userService.getAllUserDto();
            request.setAttribute(ServletAttributes.USERS_LIST, usersList);
            request.getRequestDispatcher(ServletAttributes.JSP_VIEW_USERS).forward(request, response);
        } catch (TransactionInterruptedException e) {
            LOGGER.warn(e.getMessage());
            request.setAttribute(ServletAttributes.ERROR_MESSAGE, ServletAttributes.ERROR_SERVER);
            response.sendRedirect(ServletAttributes.JSP_ERROR_PAGE);
        }
    }
}
