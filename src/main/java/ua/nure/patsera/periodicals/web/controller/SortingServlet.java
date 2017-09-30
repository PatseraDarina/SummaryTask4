package ua.nure.patsera.periodicals.web.controller;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.dto.PeriodicalDto;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.service.PeriodicalService;

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
public class SortingServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SortingServlet.class);
    private PeriodicalService periodicalService;

    @Override
    public void init() throws ServletException {
        periodicalService = (PeriodicalService) getServletContext().getAttribute(ServletAttributes.PERIODICAL_SERVICE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PeriodicalDto> periodicalList;
        String orderBy = request.getParameter(ServletAttributes.SORT);
        String sortType = request.getParameter("type");
        try {
            periodicalList = periodicalService.sort(orderBy, sortType);
            request.setAttribute(ServletAttributes.PERIODICAL_LIST, periodicalList);
            request.setAttribute(ServletAttributes.ISALL, true);
            request.getRequestDispatcher(ServletAttributes.JSP_PERIODIC_BY_CATEGORY).forward(request, response);
        } catch (TransactionInterruptedException e) {
            LOGGER.warn(e.getMessage());
        }



    }
}
