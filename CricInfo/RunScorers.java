import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.mongodb.AggregationOutput;


@WebServlet("/RunScorers")
public class RunScorers extends HttpServlet {

    static DBCollection myPlayers;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayPage(request, response, pw);
    }

    private void displayPage(HttpServletRequest request, HttpServletResponse response, PrintWriter pw)
            throws ServletException, IOException {

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        pw.print("<div id='content'><div class='post07'>");
        pw.print("<h3>CricInfo data visualization represents the name of the players and the runs scored.</h3>");
        pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>DATA VISUALIZATION</h2></center>"
                + "<div class='entry'>");

        pw.print("<h3><button class ='btn btn-primary btn-sm' id='btnGetChartData'>View Highest Run Scorers</button></h3>");
        pw.println("<div id='chart_div'></div>");

        pw.println("</div></div></div>");
        pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.println("<script type='text/javascript' src='DataVisualization.js'></script>");
        utility.printHtml("Footer.html");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ArrayList<Player> players = MongoDBDataStoreUtilities.selectPlayersForChart();
            // System.out.println("PLAYERS ________________"+players);
            String reviewJson1 = new Gson().toJson(players);
            // System.out.println("JSONNNNN_________________"+reviewJson1);
            ArrayList<Player> statistics = gettop3Runs(players);
            // System.out.println("STATISTICS------------"+statistics);
            String reviewJson = new Gson().toJson(statistics);
             System.out.println("JSON"+ reviewJson);
            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(reviewJson);

        } catch (Exception ex) {

        }

    }

    private static ArrayList<Player> gettop3Runs(ArrayList<Player> playerList) {

        ArrayList<Player> toppers = new ArrayList<Player>();
        ArrayList<Player> top10Runs = new ArrayList<>();

            for (Player player : playerList) {
                if(top10Runs.size() < 11)
                    top10Runs.add(player);

            }
            // PlayerMap.addAll(top10Runs);
            toppers.addAll(top10Runs);

        return toppers;
    }



}
