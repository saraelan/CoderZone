import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/TeamModify")

public class TeamModify extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavAdmin.html");
		if(action.equals("Addteam"))
		{


			pw.print("<div id='content15'><div class='post15'<h2 class='title meta'>");
			pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>ADD TEAM</h2></center>"

					+ "<div class='entry'>");


			pw.print("<form method='get' action='TeamCrud'>"
					+ "<table id='bestseller'><tr><td>"

					// +"<b>team : </b></td><td><input type='text' name='team' placeholder='Please mention team if adding accessories' value='' class='input'></input>"
					// + "</td></tr><tr><td>"
					+ "<b>Team Id : </b></td><td><input type='text' name='Id' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Team Name : </b></td><td><input type='text' name='teamName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Number of Matches Played : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='matches' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+"<tr><td>Image</td><td><img id=\"preview\" /><br/><input type='file' name='image' class='input' required>"
					+"</td></tr><tr><td>"
				//	+ "<b>team Manufacturer</b></td><td><input type='text' name='teamManufacturer' value='' class='input' required></input>"
				//	+ "</td></tr><tr><td>"

					+ "<b>Number of Matches Won : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='won' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
          + "<b>Number of Matches Lost : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='lost' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
          + "<b>Number of Matches Drawn : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='draw' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"

					+ "<button class='btn btn-primary btn-sm' type='submit' class='btnbuy' name='button' value='add' style='float: right;height: 20px margin: 20px; margin-right: 10px;'>Add team</button>"
					// + "</td></tr><tr><td></td><td>"

					+ "</table>"
					+ "</form>" + "</div></div></div>");



		}else if (action.equals("Updateteam"))
		{
		     pw.print("<div id='content15'><div class='post15'>");
			pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>UPDATE TEAM</h2></center>"
					+ "<div class='entry'>");

			pw.print("<form method='get' action='TeamCrud'>"
					+ "<table id='bestseller'><tr><td>"
          + "<b>Team Id : </b></td><td><input type='text' name='Id' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Team Name : </b></td><td><input type='text' name='teamName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Number of Matches Played : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='matches' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+"<tr><td>Image</td><td><img id=\"preview\" /><br/><input type='file' name='image' class='input' required>"
					+"</td></tr><tr><td>"
				//	+ "<b>team Manufacturer</b></td><td><input type='text' name='teamManufacturer' value='' class='input' required></input>"
				//	+ "</td></tr><tr><td>"

					+ "<b>Number of Matches Won : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='won' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
          + "<b>Number of Matches Lost : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='lost' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
          + "<b>Number of Matches Drawn : </b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='draw' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<button class='btn btn-primary btn-sm' type='submit' class='btnbuy' name='button' value='update' style='float: right;height: 20px margin: 20px; margin-right: 10px;'>Update team</button>"
					+ "</td></tr><tr><td></td><td>"

					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
		}else if (action.equals("Deleteteam"))
		{
			pw.print("<div id='content15'><div class='post15'>");
			pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;>DELETE TEAM </h2></center>"
					+ "<div class='entry'>");

			pw.print("<form method='get' action='TeamCrud'>"
					+ "<table style='width:100%'><tr><td>"
					+ "<b>TeamId : </b></td><td><input type='text' name='Id' value='' class='input' required></input><button class='btn btn-primary btn-sm' type='submit' class='btnbuy' name='button' value='delete' style='float: right;height: 20px margin: 20px; margin-right: 10px;'>Delete team</button>"
					+ "</td></tr><tr><td>"

					+ "</tr></table>"
					+ "</form>" + "</div></div></div>");
		}
		else{

		}
		//displayLogin(request, response, pw, false);
		utility.printHtml("Footer.html");
		}
	}
