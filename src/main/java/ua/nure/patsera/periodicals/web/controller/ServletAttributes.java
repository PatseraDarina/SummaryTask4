package ua.nure.patsera.periodicals.web.controller;

/**
 * Created by Дарина on 11.09.2017.
 */
public class ServletAttributes {

    //User
    public static final String USER_SERVICE = "userService";
    public static final String USER = "user";
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
    public static final String USER_ACCOUNT = "account";
    public static final String USERS_LIST = "usersList";
    public static final String USER_BLOCKED = "blocked";
    public static final String USER_ID = "userId";


    public static final String REGISTRATION_DTO = "registrationDTO";

    //Periodical
    public static final String PERIODICAL_SERVICE = "periodicalService";
    public static final String PERIODICAL_PRICE = "periodicalPrice";
    public static final String PERIODICAL_NAME= "periodicalName";
    public static final String PERIODICAL_CATEGORY = "periodicalCategory";
    public static final String PERIODICAL_PHOTO = "periodicalPhoto";
    public static final String PERIODICAL_LIST = "periodicalList";
    public static final String PERIODICAL_ID = "periodicalId";


    //District
    public static final String DISTRICT_SERVICE = "districtService";
    public static final String DISTRICT_LIST = "districtList";

    //Street
    public static final String STREET_SERVICE = "streetService";

    //City
    public static final String CITY_SERVICE = "cityService";
    public static final String CITY_LIST = "cityList";

    //Category
    public static final String CATEGORY = "category";
    public static final String CATEGORY_SERVICE = "categoryService";
    public static final String CATEGORY_LIST = "categoryList";
    public static final String CATEGORY_ENTERTAINMENT = "Entertainment";
    public static final String CATEGORY_HEALTH = "Health";
    public static final String CATEGORY_TECHNOLOGY = "Technology";

    //Subscription
    public static final String SUBSCRIPTION_SERVICE = "subscriptionService";
    public static final String SUBSCRIPTION_LIST = "subscriptionList";

    //Login
    public static final String LOGIN_ERROR = "loginError";
    public static final String LOGIN_DTO = "loginDto";
    public static final String IS_EXIST = "isExist";

    //Roles
    public static final String READER = "reader";
    public static final String ADMIN = "admin";

    //Transaction
    public static final String TRANSACTION_SERVICE = "transactionService";

    //JSP
    public static final String JSP_INDEX = "index.jsp";
    public static final String JSP_VIEW_PERIODICALS = "jsp/admin/viewPeriodicals.jsp";
    public static final String JSP_ERROR_PAGE = "jsp/error.jsp";
    public static final String JSP_PERIODIC_BY_CATEGORY = "/jsp/periodicByCategory.jsp";
    public static final String JSP_PERSONAL_CABINET = "/jsp/personalCabinet.jsp";
    public static final String JSP_VIEW_USERS = "jsp/admin/viewUsers.jsp";

    //Servlets
    public static final String SHOW_CATEGORY_SERVLET = "addPeriodic";
    public static final String VIEW_PERIODICAL_SERVLET = "/viewPeriodicals";
    public static final String PERSONAL_CABINET = "/personalCabinet";
    public static final String MAIN_PAGE_SERVLET = "/mainPage";
    public static final String VIEW_BY_CATEGORY_SERVLET = "/viewByCategory";


    //Filters
    public static final String FILTER_CHECK_ROLE = "checkRole";

    //Error
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String ERROR_ADDING = "Data was not be adding. Check fields.";
    public static final String ERROR_SERVER= "Server encountered a mistake. Please, try later.";
    public static final String ERROR_NO_MONEY = "You don't have enough money. Refill your account";
    public static final String ERROR_NEED_REGISTER = "You need to register or login to have access for subscribing.";
    public static final String ERROR_BLOCKED_UDER = "Unfortunatelly you are blocked =(";

    //Filters
    public static final String SORT = "sort";
    public static final String ISALL = "isAll";

    //Localization
    public static final String LANGUAGE = "language";

    public static final String CAPTCHA_SERVICE = "captchaService";
}
