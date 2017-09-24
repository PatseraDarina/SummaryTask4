package ua.nure.patsera.periodicals.web.controller.adminServlets;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.Category;
import ua.nure.patsera.periodicals.dto.PeriodicalDto;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.service.CategoryService;
import ua.nure.patsera.periodicals.service.PeriodicalService;
import ua.nure.patsera.periodicals.web.controller.ServletAttributes;

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
public class ViewPeriodicalsServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ViewPeriodicalsServlet.class);
    private PeriodicalService periodicalService;
    private CategoryService categoryService;


    @Override
    public void init() throws ServletException {
        periodicalService = (PeriodicalService) getServletContext().getAttribute(ServletAttributes.PERIODICAL_SERVICE);
        categoryService = (CategoryService) getServletContext().getAttribute(ServletAttributes.CATEGORY_SERVICE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<PeriodicalDto> periodicalList = periodicalService.getAllPeriodicalDto();
            request.setAttribute(ServletAttributes.PERIODICAL_LIST, periodicalList);
            List<Category> categoryList = categoryService.getAllCategory();
            request.setAttribute(ServletAttributes.CATEGORY_LIST, categoryList);
            request.getRequestDispatcher(ServletAttributes.JSP_VIEW_PERIODICALS).forward(request, response);
        } catch (TransactionInterruptedException e) {
            LOGGER.warn(e.getMessage());
            request.setAttribute(ServletAttributes.ERROR_MESSAGE, ServletAttributes.ERROR_SERVER);
            response.sendRedirect(ServletAttributes.JSP_ERROR_PAGE);
        }
    }
}
