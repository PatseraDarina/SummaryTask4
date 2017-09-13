package ua.nure.patsera.periodicals.web.controller.listeners;

/**
 * Created by Дарина on 10.09.2017.
 */

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.Reader;
import ua.nure.patsera.periodicals.dao.entityDaoImpl.ReaderDaoImpl;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IReaderDao;
import ua.nure.patsera.periodicals.dao.entityTransformation.ReaderTransformation;
import ua.nure.patsera.periodicals.dao.entityTransformation.ResultSetTransformation;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
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
    }

    private void initReaderService() {
        ResultSetTransformation<Reader> readerTransformation = new ReaderTransformation();
        IReaderDao readerDao = new ReaderDaoImpl(readerTransformation);

        ReaderService readerService = new ReaderService(transactionManager, readerDao);

        servletContext.setAttribute(ServletAttributes.READER_SERVICE, readerService);
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
