package ua.nure.patsera.periodicals.web.controller;

import ua.nure.patsera.periodicals.bean.Category;
import ua.nure.patsera.periodicals.bean.Periodical;
import ua.nure.patsera.periodicals.dto.PeriodicalDto;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.service.CategoryService;
import ua.nure.patsera.periodicals.service.PeriodicalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Дарина on 16.09.2017.
 */
@WebServlet
public class AddPeriodicalsServlet extends HttpServlet {
    private PeriodicalService periodicalService;
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        periodicalService = (PeriodicalService) getServletContext().getAttribute(ServletAttributes.PERIODICAL_SERVICE);
        categoryService = (CategoryService) getServletContext().getAttribute(ServletAttributes.CATEGORY_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PeriodicalDto periodicalDto = getPeriodicalDto(request);
        try {
            periodicalService.addPeriodicals(periodicalDto);
        } catch (TransactionInterruptedException e) {
            request.setAttribute(ServletAttributes.ERROR_MESSAGE, ServletAttributes.ERROR_ADDING);
        }
        //request.getRequestDispatcher(ServletAttributes.VIEW_PERIODICAL_SERVLET).forward(request, response);
        response.sendRedirect(ServletAttributes.VIEW_PERIODICAL_SERVLET);
    }

    private PeriodicalDto getPeriodicalDto(HttpServletRequest request) {
        PeriodicalDto periodical = new PeriodicalDto();
        periodical.setName(request.getParameter(ServletAttributes.PERIODICAL_NAME));
        periodical.setPrice(Double.valueOf(request.getParameter(ServletAttributes.PERIODICAL_PRICE)));
        periodical.setPhoto(request.getParameter(ServletAttributes.PERIODICAL_PHOTO));
        periodical.setCategory(request.getParameter(ServletAttributes.PERIODICAL_CATEGORY));
        return periodical;
    }
}
