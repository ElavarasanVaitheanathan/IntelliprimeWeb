package com.ip.servlet;

import java.io.IOException;

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

@WebServlet("/UpdatePaymentServlet")
public class UpdatePaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String KEY_ID = "rzp_test_Sb0fFSbUtWf4OO";
    private static final String KEY_SECRET = "xzxIV5vqu6UO09LHatLpWme0";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String amountStr = request.getParameter("amount");
        if (amountStr == null || amountStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Amount is required");
            return;
        }

        try {
            double rupees = Double.parseDouble(amountStr.trim());
            int amount = (int) Math.round(rupees * 100);

            RazorpayClient razorpay = new RazorpayClient(KEY_ID, KEY_SECRET);
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "txn_" + System.currentTimeMillis());

            Order order = razorpay.orders.create(orderRequest);

            HttpSession session = request.getSession();
            session.setAttribute("order_id", order.get("id"));
            session.setAttribute("amount", amountStr);

            JSONObject result = new JSONObject();
            result.put("order_id", String.valueOf(order.get("id")));
            result.put("amount", amount);
            result.put("amount_rupees", amountStr);

            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(result.toString());
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid amount format");
        } catch (RazorpayException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to create Razorpay order: " + e.getMessage());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected server error: " + e.getMessage());
        }
    }
}
