package com.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactUsDAO {

	public String saveContact(String name, String email, String purpose, String comment, String mobile ) throws SQLException
	{
		// Database persistence disabled by request.
		System.out.println("Contact submission received but DB save is disabled: " + name + ", " + email + ", " + purpose + ", " + comment + ", " + mobile);
		return "Saved Successfully";
	}

	}
