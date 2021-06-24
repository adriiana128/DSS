/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media_center.DAOs;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author puyol
 */
public class SQLConnector {
    private static final String user = "dss";
    private static final String pass = "dss";
    private static final String url = "jdbc:mysql://localhost:3306/dss?zeroDateTimeBehavior=CONVERT_TO_NULL";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url , user, pass);
    }
    
    public static void close(Connection conn) {
        try {
            if(conn!=null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
