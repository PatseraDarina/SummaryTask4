package ua.nure.patsera.periodicals.web.controller;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.User;
import ua.nure.patsera.periodicals.dto.PeriodicalDto;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.service.PeriodicalService;
import ua.nure.patsera.periodicals.service.SubscriptionService;
import ua.nure.patsera.periodicals.service.TransactionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 23.09.2017.
 */
@WebServlet(name = "DeleteSubscribeServlet")
public class DeleteSubscribeServlet extends HttpServlet {
    private Logger LOGGER = Logger.getLogger(DeleteSubscribeServlet.class);
    private SubscriptionService subscriptionService;
    private PeriodicalService periodicalService;
    private TransactionService transactionService;

    @Override
    public void init() throws ServletException {
        subscriptionService = (SubscriptionService) getServletContext().getAttribute(ServletAttributes.SUBSCRIPTION_SERVICE);
        transactionService = (TransactionService) getServletContext().getAttribute(ServletAttributes.TRANSACTION_SERVICE);
        periodicalService = (PeriodicalService) getServletContext().getAttribute(ServletAttributes.PERIODICAL_SERVICE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        List<PeriodicalDto> subscriptionList;
        double money;
        int idSubscription;
        User user = (User) session.getAttribute(ServletAttributes.USER);
        int idPeriodic = Integer.valueOf(request.getParameter(ServletAttributes.PERIODICAL_ID));
        int idUser = user.getId();
        double pricePeriodical = Double.valueOf(request.getParameter(ServletAttributes.PERIODICAL_PRICE));
        money = user.getAccount() + pricePeriodical;
        user.setAccount(money);
        try {
            idSubscription = subscriptionService.getSubscription(idUser, idPeriodic).getId();
            transactionService.deleteSubscription(idSubscription, user);
            subscriptionList = periodicalService.getPeriodicalDtoByReader(idUser);
            session.setAttribute(ServletAttributes.SUBSCRIPTION_LIST, subscriptionList);
            response.sendRedirect(ServletAttributes.JSP_PERSONAL_CABINET);
        } catch (TransactionInterruptedException e) {
            LOGGER.warn(e.getMessage());
            session.setAttribute(ServletAttributes.ERROR_MESSAGE, e.getMessage());
            response.sendRedirect(ServletAttributes.JSP_ERROR_PAGE);
        }
    }
}
