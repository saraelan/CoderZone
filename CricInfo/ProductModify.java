import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductModify")

public class ProductModify extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavAdmin.html");
		if(action.equals("Addproduct"))
		{


			pw.print("<div id='content15'><div class='post15'<h2 class='title meta'>");
			pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>ADD PRODUCT</h2></center>"

					+ "<div class='entry'>");


			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
					+ "<b>Product Type : </b></td><td><select name='producttype' class='input'><option value='souvenir' selected>Souvenir</option><option value='ticket'>Ticket</option></select>"
					+ "</td></tr><tr><td>"
					// +"<b>Product : </b></td><td><input type='text' name='product' placeholder='Please mention product if adding accessories' value='' class='input'></input>"
					// + "</td></tr><tr><td>"
					+ "<b>Product Id : </b></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Product Name : </b></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Product Price : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productPrice' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+"<tr><td>Image</td><td><img id=\"preview\" /><br/><input type='file' name='productImage' class='input' required>"
					+"</td></tr><tr><td>"
				//	+ "<b>Product Manufacturer</b></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
				//	+ "</td></tr><tr><td>"

					+ "<b>Product Discount : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productDiscount' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<button class='btn btn-primary btn-sm' type='submit' class='btnbuy' name='button' value='add' style='float: right;height: 20px margin: 20px; margin-right: 10px;'>Add Product</button>"
					// + "</td></tr><tr><td></td><td>"

					+ "</table>"
					+ "</form>" + "</div></div></div>");



		}else if (action.equals("Updateproduct"))
		{
		     pw.print("<div id='content15'><div class='post15'>");
			pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>UPDATE PRODUCT</h2></center>"
					+ "<div class='entry'>");

			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
					+ "<b>Product Type : </b></td><td><select name='producttype' class='input'><option value='souvenir' selected>Souvenir</option><option value='ticket'>Ticket</option></select>"
					+ "</td></tr><tr><td>"
					+ "<b>Product Id : </b></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Product Name : </b></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Product Price : </b></td><td><input type='number' step='any' name='productPrice' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+"<tr><td>Image</td><td><img id=\"preview\" /><br/><input type='file' name='productImage' class='input' required>"
					+"</td></tr><tr><td>"
					//+ "<b>Product Manufacturer</b></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
				//	+ "</td></tr><tr><td>"

					+ "<b>Product Discount : </b></td><td><input type='number' step='any' name='productDiscount' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<button class='btn btn-primary btn-sm' type='submit' class='btnbuy' name='button' value='update' style='float: right;height: 20px margin: 20px; margin-right: 10px;'>Update Product</button>"
					+ "</td></tr><tr><td></td><td>"

					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
		}else if (action.equals("Deleteproduct"))
		{
			pw.print("<div id='content15'><div class='post15'>");
			pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;>'DELETE PRODUCT </h2></center>"
					+ "<div class='entry'>");

			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table style='width:100%'><tr><td>"
					+ "<b>ProductId : </b></td><td><input type='text' name='productId' value='' class='input' required></input><button class='btn btn-primary btn-sm' type='submit' class='btnbuy' name='button' value='delete' style='float: right;height: 20px margin: 20px; margin-right: 10px;'>Delete Product</button>"
					+ "</td></tr><tr><td>"

					+ "</tr></table>"
					+ "</form>" + "</div></div></div>");
		}
		else{

		}
		//displayLogin(request, response, pw, false);
		utility.printHtml("Footer.html");
		}
	}
