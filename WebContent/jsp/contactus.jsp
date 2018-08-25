<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="com.ip.service.ContactUsService" %>
    
    <%
    
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String subject = request.getParameter("subject");
    String message = request.getParameter("message");
    
    ContactUsService contactUsService = new ContactUsService();
    String status = contactUsService.saveContact(name, email, subject, message);
    
    
    %>
<%= status %>