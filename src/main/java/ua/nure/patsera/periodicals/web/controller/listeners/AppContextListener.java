package ua.nure.patsera.periodicals.web.controller.listeners;

/**
 * Created by Дарина on 10.09.2017.
 */

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.*;
import ua.nure.patsera.periodicals.dao.entityDaoImpl.*;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.*;
import ua.nure.patsera.periodicals.dao.entityTransformation.*;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
import ua.nure.patsera.periodicals.service.*;
import ua.nure.patsera.periodicals.web.controller.ServletAttributes;
import ua.nure.patsera.periodicals.dao.utility.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class AppContextListener implements ServletContextListener {

    /**
     * Tomcat will look up the specified resource
     * name{@code name = "jdbc/periodicals"} and
     * inject an actual implementation
     * when it discovers this annotation.
     */
    //@Resource(name = "jdbc/periodicals")
    private DataSource dataSource;

    private static final Logger LOGGER = Logger.getLogger(AppContextListener.class);
    private TransactionManager transactionManager;
    private ServletContext servletContext;
    private ConnectionPool connectionPool;

    /**
     * Public constructor is required by servlet spec.
     */
    public AppContextListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();

        dataSourceInitialized();
        connectionPoolInitialized();
        transactionManagerInitialized();

        initServices();
    }

    private void initServices() {
        initReaderService();
        initCityService();
        initPeriodicalService();
    }

    private void initReaderService() {
        ResultSetTransformation<District> districtTransformation = new DistrictTransformation();
        IDistrictDao districtDao = new DistrictDaoImpl(districtTransformation);
        DistrictService districtService = new DistrictService(transactionManager, districtDao);
        ResultSetTransformation<User> readerTransformation = new UserTransformation();
        IUserDao readerDao = new UserDaoImpl(readerTransformation);
        UserService userService = new UserService(transactionManager, readerDao, districtService);
        servletContext.setAttribute(ServletAttributes.USER_SERVICE, userService);
        servletContext.setAttribute(ServletAttributes.DISTRICT_SERVICE, districtService);
    }

    private void initCityService() {
        ResultSetTransformation<City> cityTransformation = new CityTransformation();
        ICityDao cityDao = new CityDaoImpl(cityTransformation);
        CityService cityService = new CityService(transactionManager, cityDao);
        servletContext.setAttribute(ServletAttributes.CITY_SERVICE, cityService);
    }

    private void initPeriodicalService() {
        ResultSetTransformation<Category> categoryTransformation = new CategoryTransformation();
        ICategoryDao categoryDao = new CategoryDaoImpl(categoryTransformation);
        CategoryService categoryService = new CategoryService(transactionManager, categoryDao);
        servletContext.setAttribute(ServletAttributes.CATEGORY_SERVICE, categoryService);
        ResultSetTransformation<Periodical> periodicalTransformation = new PeriodicalTransformation();
        IPeriodicalsDao periodicalsDao = new PeriodicalDaoImpl(periodicalTransformation);
        PeriodicalService periodicalService = new PeriodicalService(transactionManager, periodicalsDao, categoryService);
        servletContext.setAttribute(ServletAttributes.PERIODICAL_SERVICE, periodicalService);
    }

    /**
     * This method is invoked when the Servlet Context
     * (the Web application) is undeployed or
     * Application Server shuts down.
     *
     * @param sce
     */
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void dataSourceInitialized() {
        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/periodicals");
        } catch (NamingException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException("Unable to instantiate dataSource: ", e);
        }
    }

    private void connectionPoolInitialized() {
        connectionPool = new ConnectionPool(dataSource);
    }

    private void transactionManagerInitialized() {
        transactionManager = new TransactionManager(connectionPool);
    }



}
