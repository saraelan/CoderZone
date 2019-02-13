import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TicketList")

public class TicketList extends HttpServlet {

	/* Trending Page Displays all the Tablets and their Information in Game Speed */

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();



		String name = null;
		String CategoryName = request.getParameter("name");
		HashMap<String, Ticket> hm = new HashMap<String, Ticket>();
		HashMap<String,Ticket> allgames = new HashMap<String,Ticket> ();



		try{
		     allgames = MySqlDataStoreUtilities.getticket();
		}
		catch(Exception e)
		{

		}

			hm.putAll(allgames);



		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta' style= 'width: 1073px;'>");
		pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>EVENT TICKETS</h2></center>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Ticket> entry : hm.entrySet()) {
			Ticket ticket = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<center><h4><b>" + ticket.getName() + "</b></h4></center>");
			pw.print("<strong>" +"Price : " + ticket.getPrice() + "$</strong><ul>");
			pw.print("<li id='item'><img src='images/pro/"
					+ ticket.getImage() + "' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='ticket'>"+
					"<input type='hidden' name='access' value=''>"+

					"<button class= 'btn btn-primary btn-sm' type='submit' class='btnbuy' value='Buy Now'>Add to Cart </button></form></li>");

					pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
							"<input type='hidden' name='type' value='souvenir'>"+
							"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
							"<input type='hidden' name='price' value='"+ticket.getPrice()+"'>"+
							"<input type='hidden' name='access' value=''>"+
						    "<button class ='btn btn-primary btn-sm' type='submit' value='WriteReview' class='btnreview'>Write Review</button></form></li>");
					pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
							"<input type='hidden' name='type' value='souvenir'>"+

							"<input type='hidden' name='access' value=''>"+
						    "<button class ='btn btn-primary btn-sm' type='submit' value='ViewReview' class='btnreview'>View Review</button></form></li>");


			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
