import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/TeamCrud")

public class TeamCrud extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);
			String action = request.getParameter("button");
			System.out.print(action);
			String msg = "good";
			String teamtype= "",Id="",teamName="",image="";
			int matches=0,lost=0,draw=0,win=0;
			double winpercentage=0.0,lostpercentage=0.0,winp=0.0,lostp=0.0;
			HashMap<String,Team> allteams = new HashMap<String,Team> ();

			if (action.equals("add") || action.equals("update"))
			{
				// teamtype = request.getParameter("teamtype");
				 Id   = request.getParameter("Id");
				 teamName = request.getParameter("teamName");
				 //teamPrice = Double.parseDouble(request.getParameter("teamPrice"));
				 image = request.getParameter("image");

				 matches = Integer.parseInt(request.getParameter("matches"));
				 win = Integer.parseInt(request.getParameter("won"));
				 lost = Integer.parseInt(request.getParameter("lost"));
				 draw = Integer.parseInt(request.getParameter("draw"));
			
				 winp = (double)(win/(double)matches);
				 lostp = (double)(lost/(double)matches);
				  winpercentage = (double) Math.round(winp * 100) / 100;
				  lostpercentage = (double) Math.round(lostp * 100) / 100;

			}
			else{
				Id   = request.getParameter("Id");
			}
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavAdmin.html");

			if(action.equals("add"))
			{
				//	System.out.println("JERSEY NUMBER"+jersey);
					allteams = MySqlDataStoreUtilities.getTeams();
					if(allteams.containsKey(Id)){
						msg = "team already available";
						System.out.print(Id);
					}


			else{
			//	msg = "The team related to accessories is not available";
			}

				if (msg.equals("good"))
				{
					try
					{
						msg ="team is added successfully";// MySqlDataStoreUtilities.addteams(teamId, teamName,image,teamName,jersey,matches,runs,wickets,catches,roundOff_bat,roundOff_bowl );
						utility.createTeam(Id, teamName, image,matches,win,lost, draw);
					}
					catch(Exception e)
					{
					msg = "team cannot be inserted";
					}
					msg = "team has been successfully added";
				}
			}
			else if(action.equals("update"))
			{


			if (msg.equals("good"))
			{
				try
				{
					utility.updateTeam(Id, teamName, image, matches, win, lost, draw);
					msg ="Team is updated successfully";// MySqlDataStoreUtilities.updateteams(teamId, teamName,image,teamName,jersey,matches,runs,wickets,catches,roundOff_bat,roundOff_bowl );
				}
				catch(Exception e)
				{
				msg = "Team cannot be inserted";
				}
				msg = "Team has been successfully updated";
			}
			}
			else if(action.equals("delete"))
			{
					msg = "bad";
					allteams = MySqlDataStoreUtilities.getTeams();
					if(allteams.containsKey(Id)){
						msg = "good";
					//	System.out.print("souvenir");
					}

					if (msg.equals("good"))
					{

						try
						{
						System.out.print("delete the prodcut");
						 msg = MySqlDataStoreUtilities.deleteteam(Id);
						}
						catch(Exception e)
						{
						msg = "team cannot be deleted";
						}
						 msg = "team has been successfully deleted";
					}else{
						msg = "team not available";
					}
			}

			pw.print("<div id='content'><div class='post034'><h2 class='title meta'>");
			pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>TEAM CONFIRMATION</h2></center>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:blue'>"+msg+"</h4>");
			pw.print("</div></div></div>");
			utility.printHtml("Footer.html");


	}
}
