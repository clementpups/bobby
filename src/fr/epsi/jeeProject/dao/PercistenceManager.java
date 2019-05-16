package fr.epsi.jeeProject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.hsqldb.jdbc.JDBCDriver;

public class PercistenceManager {


    public PercistenceManager() {}
    
    private static Connection connection;
    public static Connection getConnection() throws SQLException {

        if ( null == connection || connection.isClosed() ) {
        	try {
                
        		
                //Creating the connection with HSQLDB
        		DriverManager.registerDriver(new JDBCDriver());
        		String url = "jdbc:hsqldb:hsql://localhost:9003";
        		String user = "SA";
        		String pass = "";
                connection = DriverManager.getConnection(url, user, pass);
                if (connection!= null){
                   System.out.println("Connection created successfully");
                   
                }else{
                   System.out.println("Problem with creating connection");
                }
             
             }  catch (Exception e) {
                e.printStackTrace(System.out);
             }
        }

        return connection;
    }

    public static void closeConnection() throws SQLException {
        if ( null != connection && !connection.isClosed() ) {
            connection.close();
        }
    }
}