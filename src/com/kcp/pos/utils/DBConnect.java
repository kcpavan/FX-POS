/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pavankumar
 */
public class DBConnect {
    private static Connection conn;

    private static String url = "jdbc:mysql://localhost:3306/storedb";

    private static String user = "root";

    private static String pass = "";

 

    public static Connection connect() throws SQLException{

        try{

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
        }catch(ClassNotFoundException cnfe){

            System.err.println("Error: "+cnfe.getMessage());

        }catch(InstantiationException ie){

            System.err.println("Error: "+ie.getMessage());

        }catch(IllegalAccessException iae){

            System.err.println("Error: "+iae.getMessage());

        }

 

        conn = DriverManager.getConnection(url,user,pass);

        return conn;

    }

 

    public static Connection getConnection() throws SQLException{

        if(conn !=null && !conn.isClosed())

            return conn;

        connect();

        return conn;

    }
    
    
    public static void closeConnection() throws SQLException{
        if(conn !=null && !conn.isClosed())
            conn.close();
        
    }

    
}
