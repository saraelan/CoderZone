import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PlayerList")

public class PlayerList extends HttpServlet {


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	/* Checks the Tablets type whether it is microsft or apple or samsung */

		String name = null;
		String CategoryName = request.getParameter("name");
		HashMap<String, Player> hm = new HashMap<String, Player>();
		HashMap<String,Player> allPlayers = new HashMap<String,Player> ();


		/* Checks the Tablets type whether it is microsft or sony or nintendo */
		try{
		     allPlayers = MySqlDataStoreUtilities.getPlayers();
		}
		catch(Exception e)
		{

		}
		hm.putAll(allPlayers);
		name = "";
		// if (CategoryName == null)
		// {
		// 	hm.putAll(SaxParserDataStore.playermap);
		// 	name = "";
		// }
		// else
		// {
		// 	if(CategoryName.equalsIgnoreCase("Fitbit"))
		// 	{
		// 		for(Map.Entry<String,Player> entry : SaxParserDataStore.Players.entrySet())
		// 		{
		// 		  if(entry.getValue().getName().equalsIgnoreCase("Fitbit"))
		// 		  {
		// 			 hm.put(entry.getValue().getId(),entry.getValue());
		// 		  }
		// 		}
		// 		name ="Fitbit";
		// 	}
		// }
		// 	else if (CategoryName.equals("microsoft"))
		// 	{
		// 		for(Map.Entry<String,Tablet> entry : SaxParserDataStore.tablets.entrySet())
		// 		{
		// 		  if(entry.getValue().getRetailer().equals("Microsoft"))
		// 		  {
		// 			 hm.put(entry.getValue().getId(),entry.getValue());
		// 		  }
		// 		}
		// 		name = "Microsoft";
		// 	}
		// 	else if (CategoryName.equals("samsung"))
		// 	{
		// 		for(Map.Entry<String,Tablet> entry : SaxParserDataStore.tablets.entrySet())
		// 		{
		// 		  if(entry.getValue().getRetailer().equals("Samsung"))
		// 		 {
		// 			 hm.put(entry.getValue().getId(),entry.getValue());
		// 		 }
		// 		}
		// 		name = "Samsung";
		// 	}
	  //   }

		/* Header, Left Navigation Bar are Printed.

		All the tablets and tablet information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content10'><div class='post10'><h2 class='title meta' style= 'width: 1163px;'>");
		pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>PLAYERS</h2></center>");
		pw.print("</h2><div class='entry'><table id='bestseller10'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Player> entry : hm.entrySet()) {
			Player Player = entry.getValue();
			if (i % 4 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item10'>");
			pw.print("<h3><b>" + Player.getName() + "</b></h3>");
			pw.print("<h3>#" + Player.getJersey() + "</h3><ul>");
			pw.print("<li id='item10'><img src='images/pro/"
					+ Player.getImage() + "' alt='' /></li>");
					pw.print("<li><b>Team Name: </b>"+ Player.getTeam()+"</li>");
					pw.print("<li><b>Number of Matches Played: </b>"+ Player.getMatches()+"</li>");
					pw.print("<li><b>Number of Runs: </b>"+ Player.getRuns()+"</li>");
					pw.print("<li><b>Number of Wickets: </b> "+ Player.getWickets()+"</li>");
					pw.print("<li><b>Number of Catches: </b> "+ Player.getCatches()+"</li>");


					pw.print("<li><b>Batting Average: </b>"+Player.getBatAvg()+"</li>");
					pw.print("<li><b>Bowling Average: </b>"+Player.getBowlAvg()+"</li>");


			
			pw.print("</ul></div></td>");
			if (i % 4 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
	utility.printHtml("Footer.html");
	}
}
