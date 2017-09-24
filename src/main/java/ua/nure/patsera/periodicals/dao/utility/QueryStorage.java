package ua.nure.patsera.periodicals.dao.utility;

/**
 * Created by Дарина on 08.09.2017.
 */
public class QueryStorage {

        //CRUD QUERIES FOR USERS
        public static final String CREATE_USER = "INSERT INTO USER (FIRSTNAME, MIDDLENAME, LASTNAME, PHONE, FLATNUMBER, HOUSENUMBER, PASSWORD, EMAIL, IDDISTRICT, STREET) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        public static final String READ_USER_BY_ID = "SELECT * FROM USER WHERE ID=?";
        public static final String UPDATE_USER = "UPDATE USER SET FIRSTNAME = ?, MIDDLENAME = ?, LASTNAME = ?, PHONE = ?, FLATNUMBER = ?, HOUSENUMBER = ?, PASSWORD = ?, EMAIL = ?, ACCOUNT = ?, ISBLOCKED = ? WHERE ID = ?";
        public static final String DELETE_USER = "DELETE FROM USER WHERE ID=?";

        //ADDITIONAL QUERIES FOR USERS
        public static final String READ_ALL_USERS = "SELECT * FROM USER";
        public static final String READ_BY_LOGIN_DATA_USER = "SELECT * FROM USER WHERE EMAIL = ?";
        public static final String READ_USER_ROLE = "SELECT ROLE.NAME FROM USER INNER JOIN ROLE ON USER.IDROLE = ROLE.ID WHERE EMAIL = ?;";
        public static final String UPDATE_MONEY = "UPDATE USER SET ACCOUNT = ? WHERE ID = ?";
        public static final String READ_BY_LOGIN_PSWD_USER = "SELECT * FROM USER WHERE EMAIL = ? AND PASSWORD = ?";
        public static final String READ_ALL_USER_DTO = "SELECT U.LASTNAME, U.FIRSTNAME, U.MIDDLENAME, U.PHONE, U.FLATNUMBER, \n" +
                "U.HOUSENUMBER, U.STREET, DISTRICT.NAME, CITY.NAME, U.ISBLOCKED, U.ID FROM USER AS U\n" +
                "INNER JOIN DISTRICT ON U.idDistrict=DISTRICT.ID INNER JOIN CITY ON DISTRICT.IDCITY=CITY.ID";

        //CRUD QUERIES FOR CATEGORIES
        public static final String CREATE_CATEGORY = "INSERT INTO CATEGORY (NAME) VALUES (?)";
        public static final String READ_CATEGORY_BY_ID = "SELECT * FROM CATEGORY WHERE ID=?";
        public static final String UPDATE_CATEGORY = "UPDATE CATEGORY SET NAME = ? WHERE ID = ?";
        public static final String DELETE_CATEGORY = "DELETE FROM CATEGORY WHERE ID = ?";

        //ADDITIONAL QUERIES FOR CATEGORIES
        public static final String READ_ALL_CATEGORIES = "SELECT * FROM CATEGORY";
        public static final String READ_CATEGORY_BY_NAME = "SELECT * FROM CATEGORY WHERE NAME = ?";

        //CRUD QUERIES FOR DELIVERY
        public static final String CREATE_DELIVERY = "INSERT INTO DELIVERY (IDUSER, IDSUBSCRIPTION, DELIVERYDATE) VALUES (?, ?, ?)";
        public static final String READ_DELIVERY_BY_ID = "SELECT * FROM DELIVERY WHERE ID = ?";
        public static final String UPDATE_DELIVERY = "UPDATE DELIVERY SET IDUSER = ?, IDSUBSCRIPTION = ?, DELIVERYDATE = ? WHERE ID = ?";
        public static final String DELETE_DELIVERY = "DELETE FROM DELIVERY WHERE ID = ?";

        //ADDITIONAL QUERIES FOR DELIVERIES
        public static final String READ_ALL_DELIVERIES = "SELECT * FROM DELIVERY";

        //CRUD QUERIES FOR PERIODICALS
        public static final String CREATE_PERIODICALS = "INSERT INTO PERIODICALS (NAME, IDCATEGORY, PRICE, PHOTO) VALUES (?, ?, ?, ?)";
        public static final String DELETE_PERIODICALS = "DELETE FROM PERIODICALS WHERE ID = ?";
        public static final String UPDATE_PERIODICALS = "UPDATE PERIODICALS SET NAME = ?, IDCATEGORY = ?, PRICE = ?, PHOTO = ? WHERE ID = ?";
        public static final String READ_PERIODICALS_BY_ID = "SELECT * FROM PERIODICALS WHERE ID=?";

        //ADDITIONAL QUERIES FOR PERIODICALS
        public static final String READ_ALL_PERIODICALS = "SELECT * FROM PERIODICALS";
        public static final String READ_PERIODICALS_BY_NAME = "SELECT * FROM PERIODICALS WHERE NAME = ?";
        public static final String READ_PERIODICALS_BY_CATEGORY = "SELECT CATEGORY.NAME, P.NAME, P.PRICE, P.PHOTO, P.ID FROM PERIODICALS AS P INNER JOIN CATEGORY ON P.IDCATEGORY=CATEGORY.ID  WHERE CATEGORY.NAME = ?";
        public static final String READ_ALL_PERIODICALS_DTO =  "SELECT C.NAME, P.NAME, P.PRICE, P.PHOTO, P.ID FROM PERIODICALS AS P INNER JOIN CATEGORY AS C ON P.IDCATEGORY=C.ID";
        public static final String READ_ALL_PERIODICALS_BY_READER = "SELECT CATEGORY.NAME, PERIODICALS.NAME, PERIODICALS.PRICE, PERIODICALS.PHOTO, PERIODICALS.ID FROM PERIODICALS \n" +
                                                                "INNER JOIN SUBSCRIPTION AS S ON PERIODICALS.ID=S.IDPERIODICAL INNER JOIN CATEGORY ON PERIODICALS.IDCATEGORY = CATEGORY.ID WHERE S.IDUSER = ?";

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

        //CRUD QUERIES FOR SUBSCRIPTION
        public static final String CREATE_SUBSCRIPTION = "INSERT INTO SUBSCRIPTION (IDPERIODICAL, IDUSER) VALUES (?, ?)";
        public static final String READ_SUBSCRIPTION_BY_ID = "SELECT * FROM SUBSCRIPTION WHERE ID=?";
        public static final String UPDATE_SUBSCRIPTION = "UPDATE SUBSCRIPTION SET IDPERIODICAL = ?, IDUSER = ? WHERE ID = ?";
        public static final String DELETE_SUBSCRIPTION = "DELETE FROM SUBSCRIPTION WHERE ID = ?";

        //ADDITIONAL QUERIES FOR SUBSCRIPTION
        public static final String READ_ALL_SUBSCRIPTION = "SELECT * FROM SUBSCRIPTION";
        public static final String READ_USER_SUBSCRIPTION = "SELECT * FROM SUBSCRIPTION WHERE IDPERIODICAL = ? AND IDUSER = ?";
        public static final String READ_SUBSCRIPTION_ID = "SELECT ID FROM SUBSCRIPTION WHERE IDUSER = ? AND IDPERIODICAL = ?";



}
