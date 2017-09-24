package ua.nure.patsera.periodicals.web.controller;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.User;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Дарина on 22.09.2017.
 */
@WebServlet(name = "PutMoneyServlet", urlPatterns = "/putMoney")
public class PutMoneyServlet extends HttpServlet {
    private UserService userService;
    private Logger LOGGER = Logger.getLogger(PutMoneyServlet.class);

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute(ServletAttributes.USER_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            int id = ((User) session.getAttribute(ServletAttributes.USER)).getId();
            double money = Double.valueOf(request.getParameter(ServletAttributes.USER_ACCOUNT));
            User user = ((User) session.getAttribute(ServletAttributes.USER));
            double account = ((User) session.getAttribute(ServletAttributes.USER)).getAccount();
            user.setAccount(money + account);

            try {
                userService.setAccount(id, money + account);
                response.sendRedirect(ServletAttributes.JSP_PERSONAL_CABINET);
            } catch (TransactionInterruptedException e) {
                LOGGER.warn(e.getMessage());
                request.setAttribute(ServletAttributes.ERROR_MESSAGE, e.getMessage());
                response.sendRedirect(ServletAttributes.JSP_ERROR_PAGE);
            }
        }
    }
}
