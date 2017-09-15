package ua.nure.patsera.periodicals.web.controller;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.City;
import ua.nure.patsera.periodicals.bean.District;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.service.CityService;
import ua.nure.patsera.periodicals.service.DistrictService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Дарина on 14.09.2017.
 */
@WebServlet
public class ShowRegisterInfoServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ShowRegisterInfoServlet.class);

    private DistrictService districtService;
    private CityService cityService;

    @Override
    public void init() throws ServletException {
        districtService = (DistrictService) getServletContext().getAttribute(ServletAttributes.DISTRICT_SERVICE);
        cityService = (CityService) getServletContext().getAttribute(ServletAttributes.CITY_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // execute(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<District> districtList = districtService.getAllDistrict();
        List<City> cityList = cityService.getAllCity();
        request.setAttribute(ServletAttributes.DISTRICT_LIST, districtList);
        request.setAttribute(ServletAttributes.CITY_LIST, cityList);
        request.getRequestDispatcher(ServletAttributes.JSP_REGISTER).forward(request, response);
    }
}
