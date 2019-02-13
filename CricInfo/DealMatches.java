import java.io.IOException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DealMatches")

public class DealMatches extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

			HashMap<String,Product> selectedproducts=new HashMap<String,Product>();
		try
			{
		pw.print("<div class='post0009'>");
		pw.print("<center><h1 class ='deals'>Our Best Deals </h1></center>");


			String line=null;
			String TOMCAT_HOME = System.getProperty("catalina.home");

			HashMap<String,Product> productmap=MySqlDataStoreUtilities.getProdData();

			for(Map.Entry<String, Product> entry : productmap.entrySet())
			{

			if(selectedproducts.size()<2 && !selectedproducts.containsKey(entry.getKey()))
			{


			BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME+"\\webapps\\CricInfo\\DealMatches1.txt")));
			line=reader.readLine().toLowerCase();
//

			if(line==null)
			{
				pw.print("<h2 align='center'>No Offers Found</h2>");
				break;
			}
			else
			{
			do {
					line.replaceAll(" ","");

				  if(line.toLowerCase().contains(entry.getKey().toLowerCase().replaceAll(" ","")))
				  {

					// pw.print("<br>");
					// pw.print("<br>");
					pw.print("<h1 align ='center' class ='deals'>"+line+"</h1>");
					// pw.print("<br>");
					selectedproducts.put(entry.getKey(),entry.getValue());
					break;
				  }

			    }while((line = reader.readLine()) != null);

			 }
			 }
			}
			}
			catch(Exception e)
			{
			pw.print("<h2 align='center'>No Offers Found</h2>");
			}
		pw.print("</div>");
		pw.print("</div>");
		pw.print("<div class='post01'>");
		pw.print("<h2 class='title meta'>");
		pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>DEAL MATCHES</h2></center>");
		pw.print("</h2>");
		pw.print("<div class='entry'>");
		if(selectedproducts.size()==0)
		{
		pw.print("<h2 align='center'>No Deals Found</h2>");
		}
		else
		{
		pw.print("<table id='bestseller'>");
		pw.print("<tr>");
		for(Map.Entry<String, Product> entry : selectedproducts.entrySet()){
		pw.print("<td><div id='shop_item1'><h3>"+entry.getValue().getName()+"</h3>");
		pw.print("<strong>"+entry.getValue().getPrice()+"$</strong>");
		pw.print("<ul>");
		pw.print("<li id='item1'><img src='images/pro/"+entry.getValue().getImage()+"' alt='' />");
		pw.print("</li><li>");
		pw.print("<form action='Cart' method='post'><button class ='btn btn-primary btn-sm' type='submit' style='font size:20px' class='btnbuy' value='Buy Now'>Buy Now</button>");
		pw.print("<input type='hidden' name='name' value='"+entry.getKey()+"'>");
		pw.print("<input type='hidden' name='type' value='"+entry.getValue().getType()+"'>");
		pw.print("<input type='hidden' name='maker' value='"+entry.getValue().getRetailer()+"'>");
		pw.print("<input type='hidden' name='access' value=''>");
		pw.print("</form></li><li>");
		pw.print("<form action='WriteReview' method='post'><button class ='btn btn-primary btn-sm' type='submit' class='btnreview' value='WriteReview'>Write Review</button>");
		pw.print("<input type='hidden' name='name' value='"+entry.getValue().getId()+"'>");
		pw.print("<input type='hidden' name='type' value='"+entry.getValue().getType()+"'>");
		pw.print("<input type='hidden' name='maker' value='"+entry.getValue().getRetailer()+"'>");
		pw.print("<input type='hidden' name='price' value='"+entry.getValue().getPrice()+"'>");
		pw.print("</form></li>");
		pw.print("<li>");
		pw.print("<form action='ViewReview' method='post'><button class ='btn btn-primary btn-sm' type='submit' class='btnreview' value='ViewReview'>View Review</button>");
		pw.print("<input type='hidden' name='name' value='"+entry.getValue().getId()+"'>");
		pw.print("<input type='hidden' name='type' value='"+entry.getValue().getType()+"'>");
		pw.print("</form></li></ul></div></td>");
		}
		pw.print("</tr></table>");
		}
		pw.print("</div></div></div>");

	}
}
