package ua.nure.patsera.periodicals.web.controller.adminServlets;

import org.apache.log4j.Logger;
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
@WebServlet
public class DeletePeriodicalsServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DeletePeriodicalsServlet.class);
    private PeriodicalService periodicalService;

    @Override
    public void init() throws ServletException {
        periodicalService = (PeriodicalService) getServletContext().getAttribute(ServletAttributes.PERIODICAL_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        try {
            periodicalService.delete(Integer.parseInt(request.getParameter(ServletAttributes.PERIODICAL_ID)));
            response.sendRedirect(ServletAttributes.VIEW_PERIODICAL_SERVLET);
        } catch (TransactionInterruptedException e) {
            session.setAttribute(ServletAttributes.ERROR_MESSAGE, e);
            LOGGER.warn(e);
            response.sendRedirect(ServletAttributes.JSP_VIEW_PERIODICALS);
        }
    }
}
