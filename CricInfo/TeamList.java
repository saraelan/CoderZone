import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TeamList")

public class TeamList extends HttpServlet {


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();



		String name = null;
		String CategoryName = request.getParameter("name");
		HashMap<String, Team> hm = new HashMap<String, Team>();
		HashMap<String,Team> allteams = new HashMap<String,Team> ();

    // allteams = SaxParserDataStore.teammap;

		try{
		     allteams = MySqlDataStoreUtilities.getTeams();
		}
		catch(Exception e)
		{

		}
		hm.putAll(allteams);
		name = "";
		

		/* Header, Left Navigation Bar are Printed.

		All the tablets and tablet information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content7'><div class='post7'><h2 class='title meta'style= 'width: 855px;'>");
		pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>TEAM PROFILE</h2></center>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String,Team> entry : hm.entrySet()) {
			Team team = entry.getValue();
			if (i % 2 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item7'>");
			pw.print("<h3><b>" + team.getName() + "</b></h3>");
			pw.print("<h3>#" + team.getId() + "</h3><ul>");
			pw.print("<li id='item7'><img src='images/pro/"
					+ team.getImage() + "' alt='' /></li>");
			// pw.print("<li><h3>Team Name:</h3>"+ Player.getTeam()+"</li>");
			pw.print("<li><b>Number of Matches Played : </b>"+ team.getMatches()+"</li>");
			pw.print("<li><b>Win Percentage : </b>"+ team.getWonPer()+"</li>");
			pw.print("<li><b>Lost Percentage : </b>"+ team.getLostPer()+"</li>");
			pw.print("<li><b>Number of Wins : </b>"+ team.getWon()+"</li>");
			pw.print("<li><b>Number of Losses : </b>"+team.getLost()+"</li>");
			pw.print("<li><b>Number of Draws : </b>"+team.getDraw()+"</li>");

			pw.print("</ul></div></td>");
			if (i % 2 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
