package ua.nure.patsera.periodicals.web.controller;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.Category;
import ua.nure.patsera.periodicals.bean.Periodical;
import ua.nure.patsera.periodicals.bean.Subscription;
import ua.nure.patsera.periodicals.bean.User;
import ua.nure.patsera.periodicals.dto.PeriodicalDto;
import ua.nure.patsera.periodicals.exceptions.SubscriptionException;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Period;
import java.util.List;

/**
 * Created by Дарина on 22.09.2017.
 */
@WebServlet
public class AddSubscribeServlet extends HttpServlet {
    private Logger LOGGER = Logger.getLogger(AddSubscribeServlet.class);
    private SubscriptionService subscriptionService;
    private PeriodicalService periodicalService;
    private UserService userService;
    private CategoryService categoryService;
    private TransactionService transactionService;

    @Override
    public void init() throws ServletException {
        subscriptionService = (SubscriptionService) getServletContext().getAttribute(ServletAttributes.SUBSCRIPTION_SERVICE);
        periodicalService = (PeriodicalService) getServletContext().getAttribute(ServletAttributes.PERIODICAL_SERVICE);
        userService = (UserService) getServletContext().getAttribute(ServletAttributes.USER_SERVICE);
        categoryService = (CategoryService) getServletContext().getAttribute(ServletAttributes.CATEGORY_SERVICE);
        transactionService = (TransactionService) getServletContext().getAttribute(ServletAttributes.TRANSACTION_SERVICE);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        List<PeriodicalDto> subscriptionList;
        if (session.getAttribute(ServletAttributes.USER) == null) {
            session.setAttribute(ServletAttributes.ERROR_MESSAGE, ServletAttributes.ERROR_NEED_REGISTER);
            response.sendRedirect(getCurrentPage(ServletAttributes.VIEW_BY_CATEGORY_SERVLET, request.getParameter(ServletAttributes.PERIODICAL_CATEGORY)));
        } else {
            User user = (User) session.getAttribute(ServletAttributes.USER);
            double money;
            int idUser = user.getId();
            Periodical periodical;
            try {
                periodical = getPeriodical(request);
                money = user.getAccount() - periodical.getPrice();
                if (money < 0) {
                    session.setAttribute(ServletAttributes.ERROR_MESSAGE, ServletAttributes.ERROR_NO_MONEY);
                    response.sendRedirect(request.getHeader("referer"));
                } else {
                    user.setAccount(money);
                    transactionService.addSubscription(getSubscription(idUser, periodical.getId()), user);
                    subscriptionList = periodicalService.getPeriodicalDtoByReader(user.getId());
                    session.setAttribute(ServletAttributes.SUBSCRIPTION_LIST, subscriptionList);
                    response.sendRedirect(ServletAttributes.JSP_PERSONAL_CABINET);
                }
            } catch (TransactionInterruptedException e) {
                LOGGER.warn(e.getMessage());
                session.setAttribute(ServletAttributes.ERROR_MESSAGE, e.getMessage());
                response.sendRedirect(ServletAttributes.JSP_ERROR_PAGE);
            }
        }
    }

    private String getCurrentPage(String page, String category) {
        return page + "?category=" + category;
    }

    private Subscription getSubscription(int idUser, int idPeriodic) {
        Subscription subscription = new Subscription();
        subscription.setIdReader(idUser);
        subscription.setIdPeriodical(idPeriodic);
        return subscription;
    }

    private Periodical getPeriodical(HttpServletRequest request) {
        Periodical periodical = new Periodical();
        periodical.setId(Integer.valueOf(request.getParameter(ServletAttributes.PERIODICAL_ID)));
        periodical.setPhoto(request.getParameter(ServletAttributes.PERIODICAL_PHOTO));
        periodical.setPrice(Double.valueOf(request.getParameter(ServletAttributes.PERIODICAL_PRICE)));
        periodical.setIdCategory(categoryService.getCategory(request.getParameter(ServletAttributes.PERIODICAL_CATEGORY)).getId());
        periodical.setName(request.getParameter(ServletAttributes.PERIODICAL_NAME));
        return periodical;
    }
}
