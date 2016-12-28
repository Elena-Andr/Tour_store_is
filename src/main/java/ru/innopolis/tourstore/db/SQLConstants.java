package ru.innopolis.tourstore.db;

/**
 * Class contains constant SQL statements
 */
public class SQLConstants {
    public static final String DATABASE_URL_H2 = "jdbc:h2:~/tourstore";

    public static final String DROP_ALL_QUERY = "DROP ALL OBJECTS;";

    public static final String CREATE_TABLE_USERS_QUERY = "CREATE TABLE IF NOT EXISTS USERS (ID INTEGER AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50), PASSWORD BINARY(1024), ROLE VARCHAR(10) );";
    public static final String CREATE_TABLE_TOURS_QUERY = "CREATE TABLE IF NOT EXISTS TOURS (ID INTEGER AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50), DESCRIPTION VARCHAR(50), DELETED BOOLEAN );";
    public static final String CREATE_TABLE_ORDERS_QUERY = "CREATE TABLE IF NOT EXISTS ORDERS (ID INTEGER AUTO_INCREMENT PRIMARY KEY, USER_ID INTEGER, TOUR_ID INTEGER, FOREIGN KEY(USER_ID) REFERENCES Public.USERS(ID), FOREIGN KEY(TOUR_ID) REFERENCES Public.TOURS(ID));";

    public static final String INSERT_USER_QUERY = "INSERT INTO USERS (NAME, PASSWORD, ROLE) VALUES (?,?,?);";
    public static final String INSERT_TOUR_QUERY = "INSERT INTO TOURS (NAME, DESCRIPTION, DELETED) VALUES (?,?,?);";
    public static final String INSERT_ORDER_QUERY = "INSERT INTO ORDERS (USER_ID, TOUR_ID) VALUES (?,?);";

    public static final String DELETE_USER_QUERY = "DELETE FROM USERS WHERE ID=?;";
    public static final String DELETE_TOUR_QUERY = "DELETE FROM TOURS WHERE ID=?;";
    public static final String DELETE_ORDER_QUERY = "DELETE FROM ORDERS WHERE ID=?;";

    public static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM USERS";
    public static final String SELECT_ALL_TOURS_QUERY = "SELECT * FROM TOURS";
    public static final String SELECT_ALL_ORDERS_QUERY = "SELECT * FROM ORDERS";

    public static final String SELECT_USER_QUERY = "SELECT * FROM USERS WHERE ID=?";
    public static final String SELECT_TOUR_QUERY = "SELECT * FROM TOURS WHERE ID=?";
    public static final String SELECT_ORDER_QUERY = "SELECT * FROM ORDERS WHERE ID=?";

    public static final String UPDATE_TOUR_QUERY = "UPDATE TOURS SET NAME=?, DESCRIPTION=?, DELETED=? WHERE ID=?";
    public static final String UPDATE_USER_QUERY = "UPDATE USERS SET NAME=?, PASSWORD=?, ROLE=? WHERE ID=?";
    public static final String UPDATE_ORDER_QUERY = "UPDATE ORDERS SET USER_ID=?, TOUR_ID=? WHERE ID=?";
}
