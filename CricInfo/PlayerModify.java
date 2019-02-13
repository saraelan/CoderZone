import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PlayerModify")

public class PlayerModify extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavAdmin.html");
		if(action.equals("AddPlayer"))
		{


			pw.print("<div id='content28'><div class='post28'>");
			pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>ADD PLAYER</h2></center>"
					+ "<div class='entry'>");


			pw.print("<form method='get' action='PlayerCrud'>"
					+ "<table id='bestseller'><tr><td>"
					+"<b>Player Id : </b></td><td><input type='text' name='playerId' value='' class='input'></input>"
					+ "</td></tr><tr><td>"
					+"<b>Player Name : </b></td><td><input type='text' name='playerName' value='' class='input'></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Team Name : </b></td><td><input type='text' name='teamName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Jersey Number : </b></td><td><input type='text' name='jersey' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<tr><td><b>Image : </b></td><td><img id=\"preview\" /><br/><input type='file' name='image' value='' class='input' required>"
          +  "</td></tr><tr><td>"
					+ "<b>Number of Macthes Played :</b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='matches' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
				//	+ "<b>Product Manufacturer</b></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
				//	+ "</td></tr><tr><td>"

					+ "<b>Number of Runs Scored :</b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='runs' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Number of Wickets Taken :</b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='wickets' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<b>Number of Catches Caugh :</b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='catches' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<button class= 'btn btn-primary btn-sm' type='submit' class='btnbuy' name='button' value='add' style='float: right;height: 20px margin: 20px; margin-right: 10px;'>Add Player</button>"
					+ "</td></tr><tr><td></td><td>"

					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");



		}else if (action.equals("UpdatePlayer"))
		{
		     pw.print("<div id='content28'><div class='post28'>");
				 pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>UPDATE PLAYER</h2></center>"
		 				+ "<div class='entry'>");

					pw.print("<form method='get' action='PlayerCrud'>"
							+ "<table id='bestseller'><tr><td>"
							+"<b>Player Id : </b></td><td><input type='text' name='playerId' value='' class='input'></input>"
							+ "</td></tr><tr><td>"
							+"<b>Player Name : </b></td><td><input type='text' name='playerName' value='' class='input'></input>"
							+ "</td></tr><tr><td>"
							+ "<b>Team Name : </b></td><td><input type='text' name='teamName' value='' class='input' required></input>"
							+ "</td></tr><tr><td>"
							+ "<b>Jersey Number : </b></td><td><input type='text' name='jersey' value='' class='input' required></input>"
							+ "</td></tr><tr><td>"
							+ "<tr><td>Image : </td><td><img id=\"preview\" /><br/><input type='file' name='image' value='' class='input' required>"
							+  "</td></tr><tr><td>"
							+ "<b>Number of Macthes Played :</b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='matches' value='' class='input' required></input>"
							+ "</td></tr><tr><td>"
							// + "<b>Product Manufacturer</b></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
							// + "</td></tr><tr><td>"

							+ "<b>Number of Runs Scored :</b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='runs' value='' class='input' required></input>"
							+ "</td></tr><tr><td>"
							+ "<b>Number of Wickets Taken :</b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='wickets' value='' class='input' required></input>"
							+ "</td></tr><tr><td>"
							+ "<b>Number of Catches Caught :</b></td><td><input type='number' step='any' placeholder='please enter numeric data' name='catches' value='' class='input' required></input>"
							+ "</td></tr><tr><td>"
							+ "<button class ='btn btn-primary btn-sm' type='submit' class='btnbuy' name='button' value='update' style='float: right;height: 20px margin: 20px; margin-right: 10px;'>Update Player</button>"
							+ "</td></tr><tr><td></td><td>"

							+ "</td></tr></table>"
							+ "</form>" + "</div></div></div>");
		}else if (action.equals("DeletePlayer"))
		{
			pw.print("<div id='content28'><div class='post28'>");
			pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>DELETE PLAYER</h2></center>"
					+ "<div class='entry'>");

			pw.print("<form method='get' action='PlayerCrud'>"
					+ "<table style='width:100%'><tr><td>"
					+ "<b>Player Id : </b></td><td><input type='text' name='playerId' value='' class='input' required></input> <button class ='btn btn-primary btn-sm' type='submit' class='btnbuy' name='button' value='delete' style='float: right;height: 20px margin: 20px; margin-right: 10px;'>Delete Player</button>"
					+ "</td></tr><tr><td>"

					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
		}
		else{

		}
		//displayLogin(request, response, pw, false);
		utility.printHtml("Footer.html");
		}
	}
