package com.ip.service;

import java.sql.SQLException;

import com.ip.dao.ContactUsDAO;

public class ContactUsService {

	public String saveContact(String name, String email, String purpose, String comment, String mobile ) throws SQLException
	{
		System.out.println("got contact values" + name +email+purpose+comment+mobile);
		ContactUsDAO contactUsDAO = new ContactUsDAO();
		return contactUsDAO.saveContact(name, email, purpose, comment,mobile);
	}
}
