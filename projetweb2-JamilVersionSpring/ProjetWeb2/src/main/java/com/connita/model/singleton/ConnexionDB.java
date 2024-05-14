package com.connita.model.singleton;
import com.connita.model.config.ConfigDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnexionDB {
    private static Connection conn=null;
    
    public static Connection getConnection() throws SQLException{
        
        try {
            Class.forName(ConfigDB.DRIVER);
            conn = DriverManager.getConnection(ConfigDB.URL, ConfigDB.USER, ConfigDB.PASSWORD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    return conn;
    }
    
    public static void closeConnection(){
    
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
