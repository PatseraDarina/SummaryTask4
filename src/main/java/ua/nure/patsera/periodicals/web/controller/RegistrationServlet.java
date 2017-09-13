package ua.nure.patsera.periodicals.web.controller;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.dto.RegistrationDto;
import ua.nure.patsera.periodicals.exceptions.RegistrationException;
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
@WebServlet(name = "RegistrationServlet", urlPatterns = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);
    private ReaderService readerService;

    @Override
    public void init() throws ServletException {
        readerService = (ReaderService) getServletContext().getAttribute(ServletAttributes.READER_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        RegistrationDto registrationDto = getRegistrationDto(request);
        try {
            readerService.register(registrationDto);
        } catch (RegistrationException e) {
            session.setAttribute(ServletAttributes.REGISTRATION_NOT_SUCCESS, e.getMessage());
            LOGGER.warn(e.getMessage());
        }
        response.sendRedirect(ServletAttributes.JSP_INDEX);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private RegistrationDto getRegistrationDto(HttpServletRequest request) {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setFirstName(request.getParameter(ServletAttributes.READER_FIRST_NAME));
        registrationDto.setLastName(request.getParameter(ServletAttributes.READER_LAST_NAME));
        registrationDto.setMiddleName(request.getParameter(ServletAttributes.READER_MIDDLE_NAME));
        registrationDto.setCity(request.getParameter(ServletAttributes.READER_CITY));
        registrationDto.setDistrict(request.getParameter(ServletAttributes.READER_DISTRICT));
        registrationDto.setEmail(request.getParameter(ServletAttributes.READER_EMAIL));
        registrationDto.setFlatNumber((Integer.valueOf(request.getParameter(ServletAttributes.READER_FLAT_NUMBER))));
        registrationDto.setHouseNumber(request.getParameter(ServletAttributes.READER_HOUSE_NUMBER));
        registrationDto.setPassword(request.getParameter(ServletAttributes.READER_PASSWORD));
        registrationDto.setPhone(request.getParameter(ServletAttributes.READER_PHONE));
        registrationDto.setConfirmPassword(request.getParameter(ServletAttributes.READER_CONFIRM_PASSWORD));
        return registrationDto;
    }
}
