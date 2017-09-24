package ua.nure.patsera.periodicals.web.controller;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.City;
import ua.nure.patsera.periodicals.bean.District;
import ua.nure.patsera.periodicals.dto.PeriodicalDto;
import ua.nure.patsera.periodicals.service.CityService;
import ua.nure.patsera.periodicals.service.DistrictService;
import ua.nure.patsera.periodicals.service.PeriodicalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Дарина on 14.09.2017.
 */
@WebServlet
public class MainPageInfoServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(MainPageInfoServlet.class);

    private DistrictService districtService;
    private CityService cityService;
    private PeriodicalService periodicalService;

    @Override
    public void init() throws ServletException {
        districtService = (DistrictService) getServletContext().getAttribute(ServletAttributes.DISTRICT_SERVICE);
        cityService = (CityService) getServletContext().getAttribute(ServletAttributes.CITY_SERVICE);
        periodicalService = (PeriodicalService) getServletContext().getAttribute(ServletAttributes.PERIODICAL_SERVICE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        try {
            request.setCharacterEncoding("UTF-8");
            List<District> districtList = districtService.getAllDistrict();
            List<City> cityList = cityService.getAllCity();
            List<PeriodicalDto> periodicalList = periodicalService.getAllPeriodicalDto();
            session.setAttribute(ServletAttributes.DISTRICT_LIST, districtList);
            session.setAttribute(ServletAttributes.CITY_LIST, cityList);
            request.setAttribute(ServletAttributes.PERIODICAL_LIST, periodicalList);
            request.getRequestDispatcher(ServletAttributes.JSP_INDEX).forward(request, response);
        } catch(Exception ex) {
            LOGGER.warn(ex.getMessage());
            request.getRequestDispatcher(ServletAttributes.JSP_ERROR_PAGE).forward(request, response);
        }

    }
}
