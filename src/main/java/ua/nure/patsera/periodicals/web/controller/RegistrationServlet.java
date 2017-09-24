package ua.nure.patsera.periodicals.web.controller;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.User;
import ua.nure.patsera.periodicals.dto.LoginDto;
import ua.nure.patsera.periodicals.dto.RegistrationDto;
import ua.nure.patsera.periodicals.exceptions.AuthorizationException;
import ua.nure.patsera.periodicals.exceptions.RegistrationException;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.service.UserService;

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
@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute(ServletAttributes.USER_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user;
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        RegistrationDto registrationDto = getRegistrationDto(request);
        try {
            userService.register(registrationDto);
            user = userService.login(getLoginDto(request));
            session.setAttribute(ServletAttributes.USER, user);
            session.setAttribute(ServletAttributes.USER_ROLE, userService.getUserRole(user.getEmail()));
            session.setAttribute(ServletAttributes.REGISTRATION_DTO, registrationDto);
        } catch (RegistrationException e) {
            session.setAttribute(ServletAttributes.ERROR_MESSAGE, e.getMessage());
            LOGGER.warn(e.getMessage());
        } catch (AuthorizationException e) {
            session.setAttribute(ServletAttributes.ERROR_MESSAGE, e.getMessage());
            LOGGER.warn(e.getMessage());
        } catch (TransactionInterruptedException e) {
            session.setAttribute(ServletAttributes.ERROR_MESSAGE, e.getMessage());
            LOGGER.warn(e.getMessage());
        }
        response.sendRedirect(ServletAttributes.JSP_PERSONAL_CABINET);
    }

    private RegistrationDto getRegistrationDto(HttpServletRequest request) {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setFirstName(request.getParameter(ServletAttributes.USER_FIRST_NAME));
        registrationDto.setLastName(request.getParameter(ServletAttributes.USER_LAST_NAME));
        registrationDto.setMiddleName(request.getParameter(ServletAttributes.USER_MIDDLE_NAME));
        registrationDto.setCity(request.getParameter(ServletAttributes.USER_CITY));
        registrationDto.setDistrict(request.getParameter(ServletAttributes.USER_DISTRICT));
        registrationDto.setEmail(request.getParameter(ServletAttributes.USER_EMAIL));
        registrationDto.setFlatNumber((Integer.valueOf(request.getParameter(ServletAttributes.USER_FLAT_NUMBER))));
        registrationDto.setHouseNumber(request.getParameter(ServletAttributes.USER_HOUSE_NUMBER));
        registrationDto.setPassword(request.getParameter(ServletAttributes.USER_PASSWORD));
        registrationDto.setPhone(request.getParameter(ServletAttributes.USER_PHONE));
        registrationDto.setStreet(request.getParameter(ServletAttributes.USER_STREET));
        return registrationDto;
    }

    private LoginDto getLoginDto(HttpServletRequest req) {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(req.getParameter(ServletAttributes.USER_EMAIL));
        loginDto.setPassword(req.getParameter(ServletAttributes.USER_PASSWORD));
        return loginDto;
    }
}
