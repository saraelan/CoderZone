import java.io.IOException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DealMatchesUtilities")

public class DealMatchesUtilities extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

			HashMap<String,Player> selectedproducts=new HashMap<String,Player>();
		try
			{

		pw.print("<div id='content02'>");
		pw.print("<div class='post02'>");
		pw.print("<h2 class='title'>");
	//	pw.print("<a href='#'> </a></h2>");

		pw.print("<div class='entry'>");

		pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>BEST RUN SCORERS</h2></center>");


			String line=null;
			String TOMCAT_HOME = System.getProperty("catalina.home");

			HashMap<String,Player> productmap=MySqlDataStoreUtilities.getData();

			for(Map.Entry<String, Player> entry : productmap.entrySet())
			{

			if(selectedproducts.size()<2 && !selectedproducts.containsKey(entry.getKey()))
			{


			BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME+"\\webapps\\CricInfo\\DealMatches.txt")));
			line=reader.readLine().toLowerCase();
//

			if(line==null)
			{
				pw.print("<h2 align='center'>No Players Found</h2>");
				break;
			}
			else
			{
			do {

				  if(line.contains(entry.getKey()))
				  {

					pw.print("<h4>"+line+"</h4>");
				pw.print("<br>");
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
			pw.print("<h2 align='center'>No Players Found</h2>");
			}
		pw.print("</div>");
		pw.print("</div>");
		pw.print("<div class='post03'>");
		pw.print("<h2 class='title meta'>");
		pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>BEST PLAYER RECORD</h2></center>");
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
		for(Map.Entry<String, Player> entry : selectedproducts.entrySet()){
			pw.print("<td><div id='shop_item10'>");
			pw.print("<h3>" + entry.getValue().getName() + "</h3>");
			pw.print("<h3>#" + entry.getValue().getJersey() + "</h3><ul>");
			pw.print("<li id='item10'><img src='images/pro/"
					+entry.getValue().getImage() + "' alt='' /></li>");
			pw.print("<li><b>Team Name:</b>"+ entry.getValue().getTeam()+"</li>");
			pw.print("<li><b>Number of Matches Played:</b>"+ entry.getValue().getMatches()+"</li>");
			pw.print("<li><b>Number of Runs:</b>"+entry.getValue().getRuns()+"</li>");
			pw.print("<li><b>Number of Wickets:</b>"+ entry.getValue().getWickets()+"</li>");
			pw.print("<li><b>Number of Catches:</b>"+ entry.getValue().getCatches()+"</li>");


			pw.print("<li><b>Batting Average:</b>"+entry.getValue().getBatAvg()+"</li>");
			pw.print("<li><b>Bowling Average:</b>"+entry.getValue().getBowlAvg()+"</li>");

	pw.print("</ul></div></td>");
		}
		pw.print("</tr></table>");
		}
		pw.print("</div></div></div>");

	}
}
