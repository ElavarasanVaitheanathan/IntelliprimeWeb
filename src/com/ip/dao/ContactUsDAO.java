package com.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactUsDAO {

	public String saveContact(String name, String email, String purpose, String comment ) throws SQLException
	{
		try {
			ConnectionManager connectionManager = new ConnectionManager();
			//Connection connection = ConnectionManager.getLocalConnection();
			Connection connection = ConnectionManager.getRemoteConnection();
			
			PreparedStatement preparedStatement = connection
			        .prepareStatement("insert into contact_us(name,email,subject,message) values(?,?,?,?);");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, purpose);
			preparedStatement.setString(4, comment);
			int status = preparedStatement.executeUpdate();
			if(status == 1)
			{
				return "Saved Successfully";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error" ;
		}	
		return "Error" ;
	}
}
