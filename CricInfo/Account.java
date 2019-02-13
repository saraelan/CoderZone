import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.sql.*;

@WebServlet("/Account")

public class Account extends HttpServlet {
    private String error_msg;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayAccount(request, response);
    }

    /* Display Account Details of the Customer (Name and Usertype) */

    protected void displayAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        try {
            response.setContentType("text/html");
            if (!utility.isLoggedin()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("login_msg", "Please Login to add items to cart");
                response.sendRedirect("Login");
                return;
            }
            HttpSession session = request.getSession();
            utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");
            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>ORDER CONFIRMATION</h2></center>");
            pw.print("</h2><div class='entry'>");
            User user = utility.getUser();
            pw.print("<table class='gridtable'>");


            HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
            try {
                orderPayments = MySqlDataStoreUtilities.selectOrder();
            } catch (Exception e) {

            }
            int size = 0;
            for (Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()) {
                for (OrderPayment od : entry.getValue())
                    if (od.getUserName().equals(user.getName()))
                        size = size + 1;
            }

            if (size > 0) {

                pw.print("<tr>");
                pw.print("<td><b>OrderId :</b></td>");
                pw.print("<td><b>UserName : </b></td>");
                pw.print("<td><b>Product Ordered :</b></td>");
                pw.print("<td><b>Product Price :</b></td></tr>");
                for (Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()) {
                    for (OrderPayment oi : entry.getValue())
                        if (oi.getUserName().equals(user.getName())) {
                            pw.print("<form method='post' action='RemoveUpdateOrder'>");
                            pw.print("<tr>");
                          
                            pw.print("<td>" + oi.getOrderId() + "</td><td>" + oi.getUserName() + "</td><td>" + oi.getOrderName() + "</td><td>Price: " + oi.getOrderPrice() + "</td>");
                            pw.print("<input type='hidden' name='orderId' value='" + oi.getOrderId() + "'>");
                            pw.print("<td><button class ='btn btn-danger' type='submit' name='Order' value='Cancel' class='btnbuy'>Cancel</button></td>");
                            pw.print("<input type='hidden' name='orderId' value='" + oi.getOrderId() + "'>");
                            pw.print("<input type='hidden' name='productName' value='" + oi.getOrderName() + "'>");
                            pw.print("<input type='hidden' name='username' value='" + oi.getUserName() + "'>");
                            pw.print("<input type='hidden' name='userType' value='customer'>");
                            pw.print("</tr>");
                            pw.print("</form>");
                        }

                }
                pw.print("</table>");
            } else {
                pw.print("<h4 style='color:red'>You have not placed any order with this account</h4>");
            }
            pw.print("</table>");
            pw.print("</h2></div></div></div>");
            utility.printHtml("Footer.html");
        } catch (Exception e) 
		{
        }
    }
}
