import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SouvenirList")

public class SouvenirList extends HttpServlet {

	/* souvenir Page Displays all the souvenirs and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("name");

			HashMap<String, Souvenir> hm = new HashMap<String, Souvenir>();

		HashMap<String,Souvenir> allsouvenirs = new HashMap<String,Souvenir> ();


		try{
		     allsouvenirs = MySqlDataStoreUtilities.getsouvenir();
		}
		catch(Exception e)
		{

		}

		if(CategoryName==null){
			hm.putAll(allsouvenirs);
			name = "";
		}



		/* Header, Left Navigation Bar are Printed.

		All the souvenir and souvenir information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content1'><div class='post1'><h2 class='title meta' style= 'width: 1133px;'>");
			pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>SOUVENIRS</h2></center>");
			pw.print("</h2><div class='entry'><table id='bestseller'>");
			int i = 1; int size= hm.size();
			for(Map.Entry<String, Souvenir> entry : hm.entrySet())
			{
				Souvenir souvenir = entry.getValue();
				if(i%4==1)
				pw.print("<tr>");
				pw.print("<td><div id='shop_item1'>");
				pw.print("<h3><b>"+souvenir.getName()+"</b></h3>");
				pw.print("<strong>$"+souvenir.getPrice()+"</strong><ul>");
				pw.print("<li id='item1'><img src='images/pro/"+souvenir.getImage()+"' alt='' /></li>");

				pw.print("<li><form method='post' action='Cart'>" +
						"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='souvenir'>"+

						"<input type='hidden' name='access' value=''>"+
						"<button class ='btn btn-primary btn-sm' type='submit' class='btnbuy' value='Buy Now'>Buy now</button></form></li>");
				pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='souvenir'>"+
						"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
						"<input type='hidden' name='price' value='"+souvenir.getPrice()+"'>"+
						"<input type='hidden' name='access' value=''>"+
					    "<button class ='btn btn-primary btn-sm' type='submit' value='WriteReview' class='btnreview'>Write Review</button></form></li>");
				pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='type' value='souvenir'>"+

						"<input type='hidden' name='access' value=''>"+
					    "<button class ='btn btn-primary btn-sm' type='submit' value='ViewReview' class='btnreview'>View Review</button></form></li>");
				pw.print("</ul></div></td>");
				if(i%4==0 || i == size)
				pw.print("</tr>");
				i++;
			}
			pw.print("</table></div></div></div>");

		utility.printHtml("Footer.html");

	}
}
