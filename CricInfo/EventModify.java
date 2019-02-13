import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EventModify")

/* Add Event Features */

public class EventModify extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavAdmin.html");
		if(action.equals("Addevent"))
		{


			pw.print("<div id='content15'><div class='post15'<h2 class='title meta'>");
			pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>ADD EVENT</h2></center>"

					+ "<div class='entry'>");


			pw.print("<form method='get' action='EventCrud'>"
					+ "<table id='bestseller'><tr><td>"


					+ "<b>Event Id : </b></td><td><input type='text' name='eventId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Team 1 Name : </b></td><td><input type='text' name='team1' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Team 2 Name : </b></td><td><input type='text' name='team2' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Date: </b></td><td><input type='date' name='date'></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Time: </b></td><td><input type='time' name='time'></input>"
					+ "</td></tr><tr><td>"
					+ "<b>City : </b></td><td><input type='text' name='city' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Venue : </b></td><td><input type='text' name='venue' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Toss Won By : </b></td><td><input type='text' name='tosswin' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Decision Taken : </b></td><td><input type='text' name='tossdecision' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Match Winner : </b></td><td><input type='text' name='winner' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"

					+ "<b>Runs Scored By Team 1 : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='team1runs' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Runs Scored By Team 2 : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='team2runs' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Wickets Taken By Team 1 : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='team2wickets' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Wickets Taken By Team 2 : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='team1wickets' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Win By Runs : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='winbyruns' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Win By Wickets : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='winbywickets' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Player Of Match : </b></td><td><input type='text' name='playerofmatch' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<button class='btn btn-primary btn-sm' type='submit' class='btnbuy' name='button' value='add' style='float: right;height: 20px margin: 20px; margin-right: 10px;'>Add Event</button>"
					// + "</td></tr><tr><td></td><td>"

					+ "</table>"
					+ "</form>" + "</div></div></div>");



		}
		//displayLogin(request, response, pw, false);
		utility.printHtml("Footer.html");
		}
	}
