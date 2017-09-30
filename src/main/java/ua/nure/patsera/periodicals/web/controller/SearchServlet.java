package ua.nure.patsera.periodicals.web.controller;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.Periodical;
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
 * Created by Дарина on 25.09.2017.
 */
@WebServlet
public class SearchServlet extends HttpServlet {
    private Logger LOGGER = Logger.getLogger(SearchServlet.class);
    private PeriodicalService periodicalService;

    @Override
    public void init() throws ServletException {
        periodicalService = (PeriodicalService) getServletContext().getAttribute(ServletAttributes.PERIODICAL_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<PeriodicalDto> periodicalList;
        String name = request.getParameter(ServletAttributes.PERIODICAL_NAME);
        try {
            periodicalList = periodicalService.getPeriodicalDtoByName(name);
            request.setAttribute(ServletAttributes.PERIODICAL_LIST, periodicalList);
            request.setAttribute(ServletAttributes.ISALL, true);
            request.getRequestDispatcher(ServletAttributes.JSP_PERIODIC_BY_CATEGORY).forward(request, response);
        } catch (TransactionInterruptedException e) {
            LOGGER.warn(e.getMessage());
        }
    }

}
