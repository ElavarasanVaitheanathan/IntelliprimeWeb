package com.ip.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.json.JSONObject;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Replace with your Razorpay Key ID and Secret
    private static final String KEY_ID = "rzp_test_Sb0fFSbUtWf4OO";
    private static final String KEY_SECRET = "xzxIV5vqu6UO09LHatLpWme0";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String course = request.getParameter("course");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String amountStr = request.getParameter("amount");

        int amount = Integer.parseInt(amountStr) * 100; // Razorpay expects amount in paisa

        try {
            RazorpayClient razorpay = new RazorpayClient(KEY_ID, KEY_SECRET);

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "txn_" + System.currentTimeMillis());

            Order order = razorpay.orders.create(orderRequest);

            HttpSession session = request.getSession();
            session.setAttribute("order_id", order.get("id"));
            session.setAttribute("course", course);
            session.setAttribute("name", name);
            session.setAttribute("email", email);
            session.setAttribute("phone", phone);
            session.setAttribute("amount", amountStr);

            request.setAttribute("order_id", order.get("id"));
            request.setAttribute("amount", amount);
            request.setAttribute("key_id", KEY_ID);
            request.setAttribute("course", course);
            request.setAttribute("name", name);
            request.setAttribute("email", email);

            request.getRequestDispatcher("payment.jsp").forward(request, response);

        } catch (RazorpayException e) {
            throw new ServletException("Razorpay error", e);
        }
    }
}