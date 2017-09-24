package ua.nure.patsera.periodicals.web.controller.adminServlets;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.dto.PeriodicalDto;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.service.PeriodicalService;
import ua.nure.patsera.periodicals.web.controller.ServletAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Дарина on 20.09.2017.
 */
@WebServlet(name = "UpdatePeriodicalsServlet", urlPatterns = "/updatePeriodicals")
public class UpdatePeriodicalsServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UpdatePeriodicalsServlet.class);
    private PeriodicalService periodicalService;

    @Override
    public void init() throws ServletException {
        periodicalService = (PeriodicalService) getServletContext().getAttribute(ServletAttributes.PERIODICAL_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        PeriodicalDto periodical = new PeriodicalDto();
        periodical.setId(Integer.valueOf(request.getParameter(ServletAttributes.PERIODICAL_ID)));
        periodical.setName(request.getParameter(ServletAttributes.PERIODICAL_NAME));
        periodical.setPrice(Double.valueOf(request.getParameter(ServletAttributes.PERIODICAL_PRICE)));
        periodical.setPhoto(request.getParameter(ServletAttributes.PERIODICAL_PHOTO));
        periodical.setCategory(request.getParameter(ServletAttributes.PERIODICAL_CATEGORY));
        try {
            periodicalService.updatePeriodicals(periodical);
            response.sendRedirect(ServletAttributes.VIEW_PERIODICAL_SERVLET);
        } catch (TransactionInterruptedException e) {
            session.setAttribute(ServletAttributes.ERROR_MESSAGE, e.getMessage());
            LOGGER.warn(e.getMessage());
            response.sendRedirect(ServletAttributes.JSP_VIEW_PERIODICALS);
        } catch (Exception e) {
            session.setAttribute(ServletAttributes.ERROR_MESSAGE, e.getMessage());
            LOGGER.warn(e.getMessage());
            response.sendRedirect(ServletAttributes.JSP_VIEW_PERIODICALS);
        }
    }
}
