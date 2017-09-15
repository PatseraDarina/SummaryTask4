package ua.nure.patsera.periodicals.web.controller.listeners;

/**
 * Created by Дарина on 10.09.2017.
 */

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.City;
import ua.nure.patsera.periodicals.bean.District;
import ua.nure.patsera.periodicals.bean.Reader;
import ua.nure.patsera.periodicals.dao.entityDaoImpl.CityDaoImpl;
import ua.nure.patsera.periodicals.dao.entityDaoImpl.DistrictDaoImpl;
import ua.nure.patsera.periodicals.dao.entityDaoImpl.ReaderDaoImpl;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.ICityDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IDistrictDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IReaderDao;
import ua.nure.patsera.periodicals.dao.entityTransformation.CityTransformation;
import ua.nure.patsera.periodicals.dao.entityTransformation.DistrictTransformation;
import ua.nure.patsera.periodicals.dao.entityTransformation.ReaderTransformation;
import ua.nure.patsera.periodicals.dao.entityTransformation.ResultSetTransformation;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
import ua.nure.patsera.periodicals.service.CityService;
import ua.nure.patsera.periodicals.service.DistrictService;
import ua.nure.patsera.periodicals.service.ReaderService;
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

    /**
     * This method is called when the servlet context is
     * initialized(when the Web application is deployed).
     *
     * @param sce notifies about changes to
     *            the servlet context of a web application.
     */
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
    }

    private void initReaderService() {
        ResultSetTransformation<District> districtTransformation = new DistrictTransformation();
        IDistrictDao districtDao = new DistrictDaoImpl(districtTransformation);
        DistrictService districtService = new DistrictService(transactionManager, districtDao);
        ResultSetTransformation<Reader> readerTransformation = new ReaderTransformation();
        IReaderDao readerDao = new ReaderDaoImpl(readerTransformation);
        ReaderService readerService = new ReaderService(transactionManager, readerDao, districtService);
        servletContext.setAttribute(ServletAttributes.READER_SERVICE, readerService);
        servletContext.setAttribute(ServletAttributes.DISTRICT_SERVICE, districtService);
    }

    private void initCityService() {
        ResultSetTransformation<City> cityTransformation = new CityTransformation();
        ICityDao cityDao = new CityDaoImpl(cityTransformation);
        CityService cityService = new CityService(transactionManager, cityDao);
        servletContext.setAttribute(ServletAttributes.CITY_SERVICE, cityService);
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
