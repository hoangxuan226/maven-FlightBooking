/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author phamx
 */
public class DBUtils {
    static Logger logger = Logger.getLogger(DBUtils.class.getName());

    public DBUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Connection getConnection(){
        
        String url;        
        if (INSTANCE == null || INSTANCE.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + SERVER_NAME + ":" + PORT_NUMBER + ";databaseName=" + DB_NAME;
        } else{
            url = "jdbc:sqlserver://" + SERVER_NAME + ":" + PORT_NUMBER + "\\" + INSTANCE + ";databaseName=" + DB_NAME;
        }
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            logger.info("FLIGHTBOOKING: Can not load JDBC Driver. Please check your pom file");
        }
        
        try {
            return DriverManager.getConnection(url, USER_ID, PASSWORD);
        } catch (SQLException ex) {
            logger.info("FLIGHTBOOKING: Can not connect SQL Server. Reason: " + ex.getMessage());                        
        }
        return null;
    }    
    
    private static final String SERVER_NAME = "localhost";
    private static final String DB_NAME = "FlightBookingDB";
    private static final String PORT_NUMBER = "1433";
    private static final String INSTANCE = "";//LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
    private static final String USER_ID = "sa";
    private static final String PASSWORD = "12345";
}
