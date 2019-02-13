import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/Trending")

public class Trending extends HttpServlet {

    ArrayList<Mostsold> mostsold = new ArrayList<Mostsold>();
    ArrayList<Mostsoldzip> mostsoldzip = new ArrayList<Mostsoldzip>();
    ArrayList<BestRating> bestrated = new ArrayList<BestRating>();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        mostsold = MongoDBDataStoreUtilities.mostsoldProducts();
        mostsoldzip = MongoDBDataStoreUtilities.mostsoldZip();
        System.out.println("mostsoldzip: "+mostsoldzip.size());
        bestrated = MongoDBDataStoreUtilities.topProducts();

        for (Mostsoldzip mostsoldzip1: mostsoldzip){
            System.out.println(mostsoldzip1.getZipcode());
            System.out.println(mostsoldzip1.getCount());
        }


        String name = "Trending";
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        pw.print("<div id='content17'><div class='post17'><h2 class='title meta'style= 'width: 496px;'>");
        pw.print("<center><h2 class='title'style= 'width: 1099px;'>");
        pw.print("TRENDING");
        pw.print("</h2></center>");

        pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>TOP 5 MOST LIKED PRODUCTS</h2></center>");

        pw.print("</h2><div class='entry'><table id='bestseller'>");
        Iterator itr2 = bestrated.iterator();
        while (itr2.hasNext()) {
            BestRating best = (BestRating) itr2.next();
            pw.print("<tr>");
            pw.print("<td>");
            pw.print(best.getProductname().replace("_"," "));
            pw.print("</td>");
            pw.print("<td>");
            pw.print(best.getRating());
            pw.print("</td>");
            pw.print("</tr>");
        }
        pw.print("</table></div></div></div>");

        pw.print("<div id='content18'><div class='post18'><h2 class='title meta'style= 'width: 571px;'>");
        pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>TOP 5 ZIP-CODES WHERE MAXIMUM NUMBER OF PRODUCTS SOLD</h2></center>");

        pw.print("</h2><div class='entry'><table id='bestseller'>");
        Iterator itr1 = mostsoldzip.iterator();
        while (itr1.hasNext()) {
            Mostsoldzip mostzip = (Mostsoldzip) itr1.next();
            pw.print("<tr>");
            pw.println("<td border: 1px >");
            System.out.println(mostzip.getZipcode());
            pw.println(mostzip.getZipcode());
            pw.println("</td>");
            pw.println("<td border: 1px >");
            pw.println(mostzip.getCount());
            pw.println("</td>");
            pw.println("</tr>");
        }
        pw.print("</table></div></div></div>");

        pw.print("<div id='content19'><div class='post19'><h2 class='title meta'style= 'width: 476px;'>");
        pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'>TOP 5 MOST SOLD PRODUCTS REGARDLESS OF THE RATING</h2></center>");

        pw.print("</h2><div class='entry'><table id='bestseller'>");

        Iterator itr = mostsold.iterator();
        while (itr.hasNext()) {
            Mostsold most = (Mostsold) itr.next();
            pw.println("<tr>");
            pw.println("<td border: 1px >");
            pw.println(most.getProductname().replace("_"," "));
            pw.println("</td>");
            pw.println("<td border: 1px >");
            pw.println(most.getCount());
            pw.println("</td>");
            pw.println("</tr>");
        }
        pw.print("</table></div></div></div>");

        //	pw.print("</table></div></div></div>");
       utility.printHtml("Footer.html");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    }

}
