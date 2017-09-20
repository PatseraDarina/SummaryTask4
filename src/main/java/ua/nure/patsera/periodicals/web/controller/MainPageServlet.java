package ua.nure.patsera.periodicals.web.controller;

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
 * Created by Дарина on 16.09.2017.
 */
@WebServlet
public class MainPageServlet extends HttpServlet {
    private PeriodicalService periodicalService;

    @Override
    public void init() throws ServletException {
        periodicalService = (PeriodicalService) getServletContext().getAttribute(ServletAttributes.PERIODICAL_SERVICE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PeriodicalDto> periodicalList;
        try {
            periodicalList = periodicalService.getAllPeriodicalDto();
            request.setAttribute(ServletAttributes.PERIODICAL_LIST, periodicalList);
            request.getRequestDispatcher(ServletAttributes.JSP_INDEX).forward(request, response);
        } catch (TransactionInterruptedException e) {
            request.setAttribute(ServletAttributes.ERROR_MESSAGE, ServletAttributes.ERROR_SERVER);
            response.sendRedirect(ServletAttributes.JSP_ERROR_PAGE);
        }
    }
}
