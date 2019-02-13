import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PlayerCrud")

public class PlayerCrud extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);
			String action = request.getParameter("button");
			System.out.print(action);
			String msg = "good";
			String playerId = "",playerName= "",teamName="",jersey="",image="";
			int matches=0,runs=0,catches=0,wickets=0;
			double batavg=0.0,bowlavg=0.0,roundOff_bat=0.0,roundOff_bowl=0.0;
			HashMap<String,Player> allplayers = new HashMap<String,Player> ();


			if (action.equals("add") || action.equals("update"))
			{
				 playerId= request.getParameter("playerId");
				 playerName = request.getParameter("playerName");
				 teamName   = request.getParameter("teamName");
				 jersey = request.getParameter("jersey");
				 image = request.getParameter("image");
				 matches = Integer.parseInt(request.getParameter("matches"));
				 runs = Integer.parseInt(request.getParameter("runs"));
				 catches = Integer.parseInt(request.getParameter("catches"));
				 wickets = Integer.parseInt(request.getParameter("wickets"));
				// productManufacturer = request.getParameter("productManufacturer");
				 //productCondition = request.getParameter("productCondition");
				 batavg = (double)(runs/(double)matches);
				 bowlavg = (double)(wickets/(double)matches);
				  roundOff_bat = (double) Math.round(batavg * 100) / 100;
				  roundOff_bowl = (double) Math.round(bowlavg * 100) / 100;


			}
			else{
				playerId   = request.getParameter("playerId");
			}
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavAdmin.html");

			if(action.equals("add"))
			{
				//	System.out.println("JERSEY NUMBER"+jersey);
				  allplayers = MySqlDataStoreUtilities.getPlayers();
				  if(allplayers.containsKey(playerId)){
					  msg = "Player already available";
					  System.out.print(playerId);
				  }


			else{
			//	msg = "The Player related to accessories is not available";
			}

			  if (msg.equals("good"))
			  {
				  try
				  {
					  msg ="Player is added successfully";// MySqlDataStoreUtilities.addplayers(playerId, playerName,image,teamName,jersey,matches,runs,wickets,catches,roundOff_bat,roundOff_bowl );
						utility.createPlayers(playerId,playerName, image,teamName, jersey, matches,runs,wickets, catches);
				  }
				  catch(Exception e)
				  {
					msg = "Player cannot be inserted";
				  }
				  msg = "Player has been successfully added";
			  }
			}
			else if(action.equals("update"))
			{


			if (msg.equals("good"))
			{
				try
				{
					utility.updatePlayers(playerId, playerName,image,teamName,jersey,matches,runs,wickets,catches);
					msg ="Player is updated successfully";// MySqlDataStoreUtilities.updateplayers(playerId, playerName,image,teamName,jersey,matches,runs,wickets,catches,roundOff_bat,roundOff_bowl );
				}
				catch(Exception e)
				{
				msg = "Player cannot be inserted";
				}
				msg = "Player has been successfully updated";
			}
			}
			else if(action.equals("delete"))
			{
				  msg = "bad";
				  allplayers = MySqlDataStoreUtilities.getPlayers();
				  if(allplayers.containsKey(playerId)){
					  msg = "good";
					  System.out.print("souvenir");
				  }

				  if (msg.equals("good"))
				  {

					  try
					  {
						System.out.print("delete the prodcut");
						 msg = MySqlDataStoreUtilities.deleteplayers(playerId);
					  }
					  catch(Exception e)
					  {
						msg = "Player cannot be deleted";
					  }
					   msg = "Player has been successfully deleted";
				  }else{
					  msg = "Player not available";
				  }
			}

			pw.print("<div id='content'><div class='post034'><h2 class='title meta'>");
			pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>PLAYER CONFIRMATION</h2></center>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:blue'>"+msg+"</h4>");
			pw.print("</div></div></div>");
			utility.printHtml("Footer.html");

	}
}
