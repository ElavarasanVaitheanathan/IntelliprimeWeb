package com.ip.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	public static Connection getRemoteConnection() {
	    if (System.getProperty("RDS_HOSTNAME") != null) {
	      try {
	      Class.forName("com.mysql.jdbc.Driver");
	      String dbName = System.getProperty("RDS_DB_NAME");
	      String userName = System.getProperty("RDS_USERNAME");
	      String password = System.getProperty("RDS_PASSWORD");
	      String hostname = System.getProperty("RDS_HOSTNAME");
	      String port = System.getProperty("RDS_PORT");
	      String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
	      System.out.println("jdbcUrl is " + jdbcUrl);
	      System.out.println("Getting remote connection with connection string from environment variables.");
	      Connection con = DriverManager.getConnection(jdbcUrl);
	      System.out.println("Remote connection successful.");
	      return con;
	    }
	    catch (ClassNotFoundException e) { System.out.println(e.toString());}
	    catch (SQLException e) { System.out.println(e.toString());}
	    }
	    return null;
	  }
	
	public static Connection getLocalConnection() throws SQLException, ClassNotFoundException {
		 Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/";
		String userName = "root";
		String password = "mysql";
		String dbName = "ipdb";
		String driver = "com.mysql.jdbc.Driver";
		Connection connection = DriverManager.getConnection(url + dbName, userName, password);
	    return connection;
	  }
}
