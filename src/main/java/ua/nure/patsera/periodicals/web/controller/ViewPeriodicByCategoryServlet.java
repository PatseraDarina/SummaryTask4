package ua.nure.patsera.periodicals.web.controller;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.dto.PeriodicalDto;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.service.PeriodicalService;
import ua.nure.patsera.periodicals.web.controller.adminServlets.ViewPeriodicalsServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Дарина on 21.09.2017.
 */
@WebServlet(name = "ViewPeriodicByCategoryServlet", urlPatterns = "/viewByCategory")
public class ViewPeriodicByCategoryServlet extends HttpServlet {
    private static final Logger LOGGER  = Logger.getLogger(ViewPeriodicalsServlet.class);
    private PeriodicalService periodicalService;

    @Override
    public void init() throws ServletException {
        periodicalService = (PeriodicalService) getServletContext().getAttribute(ServletAttributes.PERIODICAL_SERVICE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PeriodicalDto> periodicalList;
        try {
            switch (request.getParameter(ServletAttributes.CATEGORY)) {
                case ServletAttributes.CATEGORY_HEALTH:
                    periodicalList = periodicalService.getPeriodicalDtoByCategory(ServletAttributes.CATEGORY_HEALTH);
                    break;
                case ServletAttributes.CATEGORY_ENTERTAINMENT:
                    periodicalList = periodicalService.getPeriodicalDtoByCategory(ServletAttributes.CATEGORY_ENTERTAINMENT);
                    break;
                case ServletAttributes.CATEGORY_TECHNOLOGY:
                    periodicalList = periodicalService.getPeriodicalDtoByCategory(ServletAttributes.CATEGORY_TECHNOLOGY);
                    break;
                default:
                    periodicalList = periodicalService.getAllPeriodicalDto();
            }
            request.setAttribute(ServletAttributes.PERIODICAL_LIST, periodicalList);
            request.getRequestDispatcher(ServletAttributes.JSP_PERIODIC_BY_CATEGORY).forward(request, response);
        } catch (TransactionInterruptedException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
