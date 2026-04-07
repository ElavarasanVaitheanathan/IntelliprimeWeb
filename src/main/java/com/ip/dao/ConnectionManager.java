package com.ip.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	public static Connection getRemoteConnection() {
	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      String dbName = System.getenv("DB_NAME") != null ? System.getenv("DB_NAME") : "ipdb";
	      String userName = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
	      String password = System.getenv("DB_PASS") != null ? System.getenv("DB_PASS") : "";
	      String hostname = System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : "localhost";
	      String port = System.getenv("DB_PORT") != null ? System.getenv("DB_PORT") : "3306";
	      String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?useSSL=false&serverTimezone=UTC";
	      System.out.println("jdbcUrl is " + jdbcUrl);
	      System.out.println("Getting remote connection with connection string from environment variables.");
	      Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
	      System.out.println("Remote connection successful.");
	      return con;
	    }
	    catch (ClassNotFoundException e) { System.out.println(e.toString());}
	    catch (SQLException e) { System.out.println(e.toString());}
	    return null;
	  }
	
	public static Connection getLocalConnection() throws SQLException, ClassNotFoundException {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/";
		String userName = "root";
		String password = "mysql";
		String dbName = "ipdb";
		Connection connection = DriverManager.getConnection(url + dbName + "?useSSL=false&serverTimezone=UTC", userName, password);
	    return connection;
	  }
}
