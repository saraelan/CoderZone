import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductCrud")

public class ProductCrud extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);
			//pw.print("hi");
			String action = request.getParameter("button");
			System.out.print(action);
			String msg = "good";
			String producttype= "",productId="",productName="",productImage="",productManufacturer="",productCondition="",prod = "";
			double productPrice=0.0,productDiscount = 0.0;
			HashMap<String,Souvenir> allsouvenirs = new HashMap<String,Souvenir> ();
			HashMap<String,Ticket> alltickets = new HashMap<String,Ticket> ();

			if (action.equals("add") || action.equals("update"))
			{
				 producttype = request.getParameter("producttype");
				 productId   = request.getParameter("productId");
				 productName = request.getParameter("productName");
				 productPrice = Double.parseDouble(request.getParameter("productPrice"));
				 productImage = request.getParameter("productImage");
				// productManufacturer = request.getParameter("productManufacturer");
				 //productCondition = request.getParameter("productCondition");
				 productDiscount = Double.parseDouble(request.getParameter("productDiscount"));

			}
			else{
				productId   = request.getParameter("productId");
			}
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavAdmin.html");

			if(action.equals("add"))
			{
			  	if(producttype.equals("souvenir")){
				  allsouvenirs = MySqlDataStoreUtilities.getsouvenir();
				  if(allsouvenirs.containsKey(productId)){
					  msg = "Product already available";
					  System.out.print(productId);
				  }

			  }else if(producttype.equals("ticket"))
			  {
				  alltickets = MySqlDataStoreUtilities.getticket();
				  if(alltickets.containsKey(productId)){
					  msg = "Product already available";
				  }
			  }
			else{
				msg = "The product related to accessories is not available";
			}






			  if (msg.equals("good"))
			  {
				  try
				  {

						utility.createProduct(productId, productName, productPrice, productDiscount, productImage, producttype);
						msg = "Product is added successfully";
				  }
				  catch(Exception e)
				  {
					msg = "Product cannot be inserted";
				  }
				  msg = "Product has been successfully added";
			  }
			}else if(action.equals("update"))
			{

			  if(producttype.equals("souvenir")){
				  allsouvenirs = MySqlDataStoreUtilities.getsouvenir();
				  if(!allsouvenirs.containsKey(productId)){
					  msg = "Product not available";
				  }

			  }else if(producttype.equals("ticket"))
			  {
				  alltickets = MySqlDataStoreUtilities.getticket();
				  if(!alltickets.containsKey(productId)){
					  msg = "Product not available";
				  }
			  }

			  if (msg.equals("good"))
			  {

				  try
				  {//String id, String name, Double price, Double discount, String image, String type
					utility.updateProduct(productId,productName,productPrice,productDiscount,productImage,producttype);
					msg = "Product is updated successfully";//MySqlDataStoreUtilities.updateproducts(producttype,productId,productName,productPrice,productImage,productDiscount);
				  }
				  catch(Exception e)
				  {
					msg = "Product cannot be updated";
				  }
				  msg = "Product has been successfully updated";
			  }
			}else if(action.equals("delete"))
			{
				  msg = "bad";
				  allsouvenirs = MySqlDataStoreUtilities.getsouvenir();
				  if(allsouvenirs.containsKey(productId)){
					  msg = "good";
					  System.out.print("souvenir");
				  }


				  alltickets = MySqlDataStoreUtilities.getticket();
				  if(alltickets.containsKey(productId)){
					  msg = "good";
				  }


				  if (msg.equals("good"))
				  {

					  try
					  {
						System.out.print("delete the prodcut");
						 msg = MySqlDataStoreUtilities.deleteproducts(productId);
					  }
					  catch(Exception e)
					  {
						msg = "Product cannot be deleted";
					  }
					   msg = "Product has been successfully deleted";
				  }else{
					  msg = "Product not available";
				  }
			}

			pw.print("<div id='content'><div class='post034'><h2 class='title meta'>");
			pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>PRODUCT CONFIRMATION</h2></center>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:blue'>"+msg+"</h4>");
			pw.print("</div></div></div>");
			utility.printHtml("Footer.html");


	}
}
