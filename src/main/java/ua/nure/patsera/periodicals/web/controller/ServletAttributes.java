package ua.nure.patsera.periodicals.web.controller;

/**
 * Created by Дарина on 11.09.2017.
 */
public class ServletAttributes {

    //User
    public static final String USER_SERVICE = "USERService";
    public static final String USER = "USER";
    public static final String USER_FIRST_NAME = "firstName";
    public static final String USER_LAST_NAME = "lastName";
    public static final String USER_MIDDLE_NAME = "middleName";
    public static final String USER_CITY = "city";
    public static final String USER_DISTRICT = "district";
    public static final String USER_FLAT_NUMBER = "flatNumber";
    public static final String USER_HOUSE_NUMBER = "houseNumber";
    public static final String USER_EMAIL = "email";
    public static final String USER_PHONE = "phone";
    public static final String USER_STREET = "street";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "role";


    public static final String REGISTRATION_DTO = "registrationDTO";

    //Periodical
    public static final String PERIODICAL_SERVICE = "periodicalService";
    public static final String PERIODICAL_PRICE = "pricePeriodical";
    public static final String PERIODICAL_NAME= "namePeriodical";
    public static final String PERIODICAL_CATEGORY = "categoryPeriodical";
    public static final String PERIODICAL_PHOTO = "photoPeriodical";
    public static final String PERIODICAL_LIST = "periodicalList";
    public static final String PERIODICAL_ID = "idPeriodical";


    //District
    public static final String DISTRICT_SERVICE = "districtService";
    public static final String DISTRICT_LIST = "districtList";

    //Street
    public static final String STREET_SERVICE = "streetService";

    //City
    public static final String CITY_SERVICE = "cityService";
    public static final String CITY_LIST = "cityList";

    //Category
    public static final String CATEGORY_SERVICE = "categoryService";
    public static final String CATEGORY_LIST = "categoryList";

    //Login
    public static final String LOGIN_ERROR = "loginError";
    public static final String LOGIN_DTO = "loginDto";
    public static final String IS_EXIST = "isExist";

    //Roles
    public static final String READER = "reader";
    public static final String ADMIN = "admin";

    //Registration
    public static final String REGISTRATION_NOT_SUCCESS = "registration was not successful";

    //JSP
    public static final String JSP_INDEX = "index.jsp";
    public static final String JSP_PERCONAL_CABINET = "personalCabinet.jsp";
    public static final String JSP_REGISTER = "jsp/register.jsp";
    public static final String JSP_ADD_PERIODICALS = "jsp/admin/addPeriodicals.jsp";
    public static final String JSP_VIEW_PERIODICALS = "jsp/admin/viewPeriodicals.jsp";
    public static final String JSP_ERROR_PAGE = "jsp/error.jsp";

    //Servlets
    public static final String SHOW_CATEGORY_SERVLET = "addPeriodic";
    public static final String VIEW_PERIODICAL_SERVLET = "/viewPeriodicals";

    //Filters
    public static final String FILTER_CHECK_ROLE = "checkRole";

    //Error
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String ERROR_ADDING = "Data was not be adding. Check fields.";
    public static final String ERROR_SERVER= "Server encountered a mistake. Please, try later.";

}
