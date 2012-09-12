/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author pbhimanna
 */
public class KCPUtils {
    
    public static boolean isNullString(String str) {
		boolean result = false;
		if (str == null || str == "" || str.length() == 0) {
			result = true;
		}
		return result;
	}
    
    public static java.sql.Connection getJDBCConnection(){
		Connection conn = null;
		InputStream is = DBConnect.class.getClassLoader().getResourceAsStream("ServerConnections.properties");
		Properties props = new Properties();
		try{
		props.load(is);
		is.close();
		}catch(IOException ie){
			//logger.error(ie.getMessage());
                    ie.printStackTrace();;
		}
		finally{
			try{
				is.close();
			}catch(Exception e){
				
			}
		}
		String serverName = props.getProperty("servername");
		String portNumber = props.getProperty("portnumber");
		String databaseName = props.getProperty("databasename");
		String userName = props.getProperty("username");
		String password = props.getProperty("password");
		    	    
	    String url = "jdbc:mysql://"+serverName+":"+portNumber+"/"+databaseName;
	    try {
			Class.forName("com.mysql.jdbc.Driver");
			conn= DriverManager.getConnection(url, userName,password);
		} catch (Exception e) {
			
			e.printStackTrace();;
		}
		return conn;
	}
	public static java.sql.Connection getJDBCProjectConnection(){
		Connection conn = null;
		InputStream is = DBConnect.class.getClassLoader().getResourceAsStream("ServerConnections.properties");
		Properties props = new Properties();
		try{
		props.load(is);
		is.close();
		}catch(IOException ie){
			ie.printStackTrace();
		}
		finally{
			try{
				is.close();
			}catch(Exception e){
				
			}
		}
		String serverName = props.getProperty("servername");
		String portNumber = props.getProperty("portnumber");
		String databaseName = props.getProperty("ptcprojectdatabasename");
		String userName = props.getProperty("username");
		String password = props.getProperty("password");
		    	    
	    String url = "jdbc:mysql://"+serverName+":"+portNumber+"/"+databaseName;
	    try {
			Class.forName("com.mysql.jdbc.Driver");
			conn= DriverManager.getConnection(url, userName,password);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return conn;
	}
	public static boolean isNullList(List list) {
		if (list != null && list.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isNumeric(String value) {

		boolean returntype = false;
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(value);
		boolean matchFound = m.matches();
		if (!matchFound) {
			returntype = true;
		}
		return returntype;
	}

	public static boolean isNumericDot(String value) {
		boolean returntype = false;
		Pattern p = Pattern.compile("[.0-9]+");
		Matcher m = p.matcher(value);
		boolean matchFound = m.matches();
		if (!matchFound) {
			returntype = true;
		}
		return returntype;
	}

	public static boolean isAlphaNumeric(String value) {

		boolean returntype = false;
		Pattern p = Pattern.compile("[a-zA-Z0-9]+");
		Matcher m = p.matcher(value);
		boolean matchFound = m.matches();
		if (!matchFound) {
			returntype = true;
		}
		return returntype;
	}

	public static boolean isAlphabetic(String value) {

		boolean returntype = false;
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(value);
		boolean matchFound = m.matches();
		if (!matchFound) {
			returntype = true;
		}
		return returntype;
	}
    
}
