<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="com.ip.service.ContactUsService" %>
    
    <%
    
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String purpose = request.getParameter("subject");
    String comment = request.getParameter("message");
    String mobile = request.getParameter("mobile");
    
    ContactUsService contactUsService = new ContactUsService();
    String status = contactUsService.saveContact(name, email, purpose, comment, mobile);
    
    
    %>
<%= status %>