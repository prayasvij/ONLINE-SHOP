/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection conn;
//    final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
//    final String DB_URL = "jdbc:oracle:thin:@//DESKTOP-2B7144J:1521/XE";
//    private static final String USER = "advjava";
//    private static String PASS = "student";

    static {

        try {
            Class.forName("oracle.jdbc.OracleDriver");//replace with JDBC_DRIVER
            System.out.println("driver loaded");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-2B7144J:1521/XE", "onlineshopping", "shopping");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("EXCEPTION in opening the connection" + ex);
        }

    }

    public static Connection getConnection() {
        return conn;
    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("EXCEPTION IN CLOSEING THE db CONNECTION" + ex);
        }
    }
}
