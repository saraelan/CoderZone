import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EventList")

public class EventList extends HttpServlet {

	/* EventList page displays the events which includes details about match venue, toss decidion, runs,wickets ,score  */

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


  String city="";
  String date="";
  String team1="";
  String team2="";
  String toss_win="";
 String toss_decision="";
 String winner="";
  int win_by_runs=0;
  int win_by_wickets=0;
  String player_of_match="";
  String venue="";
  int team1_runs=0;
  int team2_runs=0;
  int team1_wickets=0;
  int team2_wickets=0;
  // String Id="";
 String time="";
		String name = null;
		String CategoryName = request.getParameter("name");
		HashMap<String, Event> hm = new HashMap<String, Event>();

		 hm.putAll(SaxParserDataStore.eventmap);


		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content21'><div class='post21'><h2 class='title meta'>");

		pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>EVENTS</h2></center>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Event> entry : hm.entrySet()) {
			Event r = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
        pw.print("<table class='gridtable21'>");
        pw.print("<tr>");
        team1 = r.getTeam1();
        team2 = r.getTeam2();
        pw.print("<td>" + team1 + " " + "vs" + " " + team2+ "</td>");
        pw.print("</tr>");
        pw.println("</table>");

			pw.print("<form method='post' action='ViewEvent'>"+
      "<input type='hidden' name='Id' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='ticket'>"+

					"<input type='hidden' name='access' value=''>"+
				    "<button class= 'btn btn-primary btn-sm' type='submit' value='ViewEvent' class='btnreview'>View Event</button></form>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
