package ua.nure.patsera.periodicals.web.controller;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.Reader;
import ua.nure.patsera.periodicals.dto.LoginDto;
import ua.nure.patsera.periodicals.exceptions.AuthorizationException;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.service.ReaderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Дарина on 11.09.2017.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private ReaderService readerService;

    @Override
    public void init() throws ServletException {
        readerService = (ReaderService) getServletContext().getAttribute(ServletAttributes.READER_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        LoginDto loginDto = getLoginDto(request);
        Reader reader = null;
        try {
            reader = readerService.login(loginDto);
        } catch (AuthorizationException e) {
            session.setAttribute(ServletAttributes.LOGIN_ERROR, e.getMessage());
            session.setAttribute(ServletAttributes.LOGIN_DTO, loginDto);
            LOGGER.warn(e.getMessage());
        } catch (TransactionInterruptedException e) {
            LOGGER.warn(e.getMessage());
        }
        request.getSession().setAttribute(ServletAttributes.READER, reader);
        response.sendRedirect(ServletAttributes.JSP_INDEX);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private LoginDto getLoginDto(HttpServletRequest req) {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(req.getParameter("email"));
        loginDto.setPassword(req.getParameter("password"));
        return loginDto;
    }
}
