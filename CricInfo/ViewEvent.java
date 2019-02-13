
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/ViewEvent")

public class ViewEvent extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        event(request, response);


    }

    protected void event(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request, pw);
             String Id = request.getParameter("Id");
             ArrayList<Event> events = MongoDBDataStoreUtilities.selectEventsForChart(Id);

             ArrayList<Event> statistics = getEventDetails(events);

             String reviewJson = new Gson().toJson(statistics);
              System.out.println("JSON"+ reviewJson);
            HashMap<String, ArrayList<Event>> hm = MongoDBDataStoreUtilities.selectEvent();
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

            utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");

            pw.print("<div id='content25'><div class='post25'><h2 class='title meta'>");
            pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>MATCH DETAILS</h2></center>");
            pw.print("</h2><div class='entry'>");

            //if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
            if (hm == null) {
                pw.println("<h2>Mongo Db server is not up and running</h2>");
            } else {
                if (!hm.containsKey(Id)) {
                    pw.println("<h2>No events</h2>");
                } else {
                  System.out.println("PRINTING RESULTS OF MATCH");
                    for (Event r : hm.get(Id)) {
                        pw.print("<table class='gridtable27'>");
                        pw.print("<tr>");
                      //  pw.print("<td> : </td>");
                        team1 = r.getTeam1();
                        team2 = r.getTeam2();
                        pw.print("<td><b>" + team1 + " " + "vs" + " "+ team2+ "</b></td>");
                        pw.print("</tr>");


                        pw.print("<tr>");

                        toss_win = r.getTossWin();
                        toss_decision = r.getTossDecision();
                        pw.print("<td>" + toss_win + " " +  "Won the toss and decided to go for"+ " " + toss_decision + "first</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");

                        venue = r.getVenue();
                        date = r.getDate();
                        time = r.getTime();
                        city = r.getCity();
                        pw.print("<td>Match held at" + " " + venue + " " + "in" + " " + city + "at" + " " + date +" " +time+ "</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");

                        winner = r.getWinner();
                        win_by_runs = r.getWinByRuns();
                        win_by_wickets = r.getWinByWickets();


                        pw.print("<td>" + winner + " " + "won the match by " + " " +  win_by_runs + "runs and "+ " " + win_by_wickets + " " +"wickets.</td>");
                        pw.print("</tr>");

                        pw.print("<tr>");

                        team1_runs = r.getTeam1Runs();
                        team2_runs = r.getTeam2Runs();
                        pw.print("<td>" + team1 + " " + "scored" + " " + team1_runs + " "+  "runs and "+ " " + team2 + " " + "scored" + " " + team2_runs + " " +"runs</td>");
                        pw.print("</tr>");


                        pw.print("<tr>");

                        team1_wickets = r.getTeam1Wickets();
                        team2_wickets = r.getTeam2Wickets();
                        pw.print("<td>" + team1 + " " + "took "+ " " +  team1_wickets + " " + "wickets and " + " " + team2 + " " + "took "+ team2_wickets + " " + "wickets" + "</td>");
                        pw.print("</tr>");


                        pw.print("<tr>");

                        player_of_match = r.getPlayerOfMatch();
                        pw.print("<td> Man of the Match :" + player_of_match + "</td>");
                        pw.print("</tr>");
                          pw.print("</tr>");



                        pw.println("</table>");
                    }

                }
            }
            pw.print("</div></div></div>");


            //Chart button
            pw.print("<div id='content026'><div class='post026'>");
            // pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Data Visualization</a></h2>"
            //         + "<div class='entry'>");
            //
             pw.print("<h3><button class='btn btn-primary btn-sm' id='getChartData' onclick = drawChart("+reviewJson+")>View Match Data</button></h3>");
            pw.println("<div id='chart_div'></div>");

            pw.println("</div></div></div>");
            pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
            pw.println("<script type='text/javascript' src='EventDetails.js'></script>");
            utility.printHtml("Footer.html");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }

    private static ArrayList<Event> getEventDetails(ArrayList<Event> eventList) {



        //get top 3 reviews for every city;
        ArrayList<Event> toppers = new ArrayList<Event>();
        ArrayList<Event> eventhigh = new ArrayList<>();

            for (Event event : eventList) {
                //if(eventhigh.size() < 11)
                    eventhigh.add(event);

            }
            // PlayerMap.addAll(top10Runs);
            toppers.addAll(eventhigh);

        return toppers;
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

    }
}
