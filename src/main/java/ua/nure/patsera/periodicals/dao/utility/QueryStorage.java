package ua.nure.patsera.periodicals.dao.utility;

/**
 * Created by Дарина on 08.09.2017.
 */
public class QueryStorage {

        //CRUD QUERIES FOR READERS
        public static final String CREATE_READER = "INSERT INTO READER (FIRSTNAME, MIDDLENAME, LASTNAME, PHONE, FLATNUMBER, HOUSENUMBER, PASSWORD, EMAIL, IDDISTRICT, STREET) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        public static final String READ_READER_BY_ID = "SELECT * FROM READER WHERE ID=?";
        public static final String UPDATE_READER = "UPDATE READER SET FIRSTNAME = ?, MIDDLENAME = ?, LASTNAME = ?, PHONE = ?, FLATNUMBER = ?, HOUSENUMBER = ?, PASSWORD = ?, EMAIL = ? WHERE ID = ?";
        public static final String DELETE_READER = "DELETE FROM READER WHERE ID=?";

        //ADDITIONAL QUERIES FOR READERS
        public static final String READ_ALL_READERS = "SELECT * FROM READER";
        public static final String READ_BY_LOGIN_DATA_READER = "SELECT * FROM READER WHERE EMAIL = ? AND PASSWORD = ?";

        //CRUD QUERIES FOR CATEGORIES
        public static final String CREATE_CATEGORY = "INSERT INTO CATEGORY (NAME) VALUES (?)";
        public static final String READ_CATEGORY_BY_ID = "SELECT * FROM CATEGORY WHERE ID=?";
        public static final String UPDATE_CATEGORY = "UPDATE CATEGORY SET NAME = ? WHERE ID = ?";
        public static final String DELETE_CATEGORY = "DELETE FROM CATEGORY WHERE ID = ?";

        //ADDITIONAL QUERIES FOR CATEGORIES
        public static final String READ_ALL_CATEGORIES = "SELECT * FROM CATEGORY";

        //CRUD QUERIES FOR DELIVERY
        public static final String CREATE_DELIVERY = "INSERT INTO DELIVERY (IDREADER, IDSUBSCRIPTION, DELIVERYDATE) VALUES (?, ?, ?)";
        public static final String READ_DELIVERY_BY_ID = "SELECT * FROM DELIVERY WHERE ID = ?";
        public static final String UPDATE_DELIVERY = "UPDATE DELIVERY SET IDREADER = ?, IDSUBSCRIPTION = ?, DELIVERYDATE = ? WHERE ID = ?";
        public static final String DELETE_DELIVERY = "DELETE FROM DELIVERY WHERE ID = ?";

        //ADDITIONAL QUERIES FOR DELIVERIES
        public static final String READ_ALL_DELIVERIES = "SELECT * FROM DELIVERY";

        //CRUD QUERIES FOR NAME_PERIODICALS
        public static final String CREATE_NAME_PERIODICALS = "INSERT INTO NAME_PERIODICALS (NAME, IDTOPIC, IDCATEGORY) VALUES (?, ?, ?)";
        public static final String READ_NAME_PERIODICALS_BY_ID = "SELECT * FROM NAME_PERIODICALS WHERE ID = ?";
        public static final String UPDATE_NAME_PERIODICALS = "UPDATE NAME_PERIODICALS SET NAME = ?, IDTOPIC = ?, IDCATEGORY = ?";
        public static final String DELETE_NAME_PERIODICALS = "DELETE FROM NAME_PERIODICALS WHERE ID = ?";

        //CRUD QUERIES FOR CITY
        public static final String CREATE_CITY = "INSERT INTO CITY (NAME) VALUES (?)";
        public static final String READ_CITY_BY_ID = "SELECT * FROM CITY WHERE ID=?";
        public static final String UPDATE_CITY = "UPDATE CITY SET NAME = ? WHERE ID = ?";
        public static final String DELETE_CITY = "DELETE FROM CITY WHERE ID = ?";

        //ADDITIONAL QUERIES FOR CITY
        public static final String READ_ALL_CITIES = "SELECT * FROM CITY";
        public static final String READ_CITY_BY_NAME = "SELECT * FROM CITY WHERE NAME = ?";

        //CRUD QUERIES FOR DISTRICT
        public static final String CREATE_DISTRICT = "INSERT INTO DISTRICT (NAME, IDCITY) VALUES (?, ?)";
        public static final String READ_DISTRICT_BY_ID = "SELECT * FROM DISTRICT WHERE ID=?";
        public static final String UPDATE_DISTRICT = "UPDATE DISTRICT SET NAME = ? WHERE ID = ?";
        public static final String DELETE_DISTRICT = "DELETE FROM DISTRICT WHERE ID = ?";

        //ADDITIONAL QUERIES FOR DISTRICT
        public static final String READ_ALL_DISTRICT = "SELECT * FROM DISTRICT";
        public static final String READ_DISTRICT_BY_NAME = "SELECT * FROM DISTRICT WHERE NAME = ?";

}
