package com.ip.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ip.service.ContactUsService;

@WebServlet("/contact")
public class ContactUsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String purpose = request.getParameter("subject");
        String comment = request.getParameter("message");
        String mobile = request.getParameter("mobile");

        String status;
        try {
            ContactUsService contactUsService = new ContactUsService();
            status = contactUsService.saveContact(name, email, purpose, comment, mobile);
        } catch (SQLException e) {
            throw new ServletException("Unable to save contact message", e);
        }

        response.getWriter().write(status);
    }
}
