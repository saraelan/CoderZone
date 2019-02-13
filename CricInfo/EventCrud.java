import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EventCrud")

public class EventCrud extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);
			String action = request.getParameter("button");
			System.out.print(action);
			String msg = "good";
			String eventId = "",team1= "",team2="",date="",time="",city="",venue="",toss="",decision="",winner="",playerofmatch="";
			int team1runs=0,team2runs=0,team1wickets=0,team2wickets=0,winbyruns=0,winbywickets=0;
			//double batavg=0.0,bowlavg=0.0,roundOff_bat=0.0,roundOff_bowl=0.0;
			HashMap<String,Event> allevents = new HashMap<String,Event> ();


				 eventId= request.getParameter("eventId");
				 team1 = request.getParameter("team1");
				 team2   = request.getParameter("team2");
				 date = request.getParameter("date");
				 city = request.getParameter("city");
				 venue = request.getParameter("venue");
				 toss = request.getParameter("tosswin");
				 decision = request.getParameter("tossdecision");
				 winner = request.getParameter("winner");
				 playerofmatch = request.getParameter("playerofmatch");
				 team1runs = Integer.parseInt(request.getParameter("team1runs"));
				 team2runs = Integer.parseInt(request.getParameter("team2runs"));
				 team1wickets = Integer.parseInt(request.getParameter("team1wickets"));
				 team2wickets = Integer.parseInt(request.getParameter("team2wickets"));
				 winbyruns = Integer.parseInt(request.getParameter("winbyruns"));
				 winbywickets = Integer.parseInt(request.getParameter("winbywickets"));



			utility.printHtml("Header.html");
			utility.printHtml("LeftNavAdmin.html");

			 if(action.equals("add"))
			 {
				 msg = "Event added Successfully";
				 utility.storeEvent(eventId,team1,team2,date,time,city,
																					 toss,decision,winner,winbyruns,
																					 winbywickets,playerofmatch,venue,team1runs,team2runs,team1wickets,team2wickets );

			 	  }

			 else{
				msg = "Event is not available";
			 }




				 



			pw.print("<div id='content'><div class='post034'><h2 class='title meta'>");
			pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>EVENT CONFIRMATION</h2></center>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:blue'>"+msg+"</h4>");
			pw.print("</div></div></div>");
			utility.printHtml("Footer.html");

	}
}
