package ua.nure.patsera.periodicals.web.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Дарина on 24.09.2017.
 */
@WebServlet
public class LocalizationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String locale = request.getParameter("language");
        session.setAttribute(ServletAttributes.LANGUAGE, locale);
        response.sendRedirect(ServletAttributes.MAIN_PAGE_SERVLET);
    }
}
