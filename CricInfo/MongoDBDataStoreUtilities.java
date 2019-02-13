//import apple.laf.JRSUIConstants;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import java.util.*;

public class MongoDBDataStoreUtilities {
    static DBCollection myEvents;
    static DBCollection myReviews;
    static DBCollection myPlayers;
    // Utilities utility = new Utilities(request, pw);

    public static DBCollection getConnection() {
        MongoClient mongo;
        mongo = new MongoClient("localhost", 27017);

        DB db = mongo.getDB("Cricket");
        myEvents = db.getCollection("myEvents");

        return myEvents;
    }

    public static DBCollection getConnection1() {
        MongoClient mongo;
        mongo = new MongoClient("localhost", 27017);

        DB db = mongo.getDB("Cricket");
        myReviews = db.getCollection("myReviews");
        return myReviews;
    }

    public static DBCollection getConnection2() {
        MongoClient mongo;
        mongo = new MongoClient("localhost", 27017);

        DB db = mongo.getDB("Cricket");
        myPlayers = db.getCollection("myPlayers");
        return myPlayers;
    }


    public static void main(String[] args) {

        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("Cricket");
            System.out.println("Connect to database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }


    public static void insertEvent() {
        String msg = "Unsuccessful";
        HashMap<String, ArrayList<Event>> events = new HashMap<String, ArrayList<Event>>();
        try {
            events = MongoDBDataStoreUtilities.selectEvent();
        } catch (Exception e) {

        }
        if (!events.containsKey("Id")) {
          try {
              getConnection();

              for(Map.Entry<String,Event> entry : SaxParserDataStore.eventmap.entrySet())
              {
                 Event acc = entry.getValue();
              // String msg1 =   utility.storeEvent(acc.getId(),acc.getTeam1(),acc.getTeam2(),acc.getDate(),acc.getTime(),acc.getTossWin(),acc.getTossDecision(),acc.getWinner(),
              //                         acc.getWinByRuns(),acc.getWinByWickets(),acc.getPlayerOfMatch(),acc.getVenue(),acc.getTeam1Runs(),acc.getTeam2Runs(),
              //                         acc.getTeam1Wickets(),acc.getTeam2Wickets());

                BasicDBObject doc = new BasicDBObject("title", "myEvents").
                        append("Id",acc.getId() ).
                        append("Team1", acc.getTeam1()).
                        append("Team2", acc.getTeam2()).
                        append("Date", acc.getDate()).
                        append("Time", acc.getTime()).
                        append("City",acc.getCity()).
                        append("toss_win", acc.getTossWin()).
                        append("toss_decision", acc.getTossDecision()).
                        append("winner", acc.getWinner()).
                        append("win_by_runs", acc.getWinByRuns()).
                        append("win_by_wickets", acc.getWinByWickets()).
                        append("player_of_match", acc.getPlayerOfMatch()).
                        append("venue", acc.getVenue()).
                        append("team1_runs", acc.getTeam1Runs()).
                        append("team2_runs", acc.getTeam2Runs()).
                        append("team1_wickets", acc.getTeam1Wickets()).
                        append("team2_wickets", acc.getTeam2Wickets());
                myEvents.insert(doc);
                 msg = "Successful";
            }

              }

              catch (Exception e) {
                  msg =  "UnSuccessful";
              }

        }

            //return msg;


    }


    //Event2

    public static void insertEvent2(String Id, String Team1, String Team2, String Date, String Time, String city,
                                      String toss_win, String toss_decision, String winner, int win_by_runs,
                                      int win_by_wickets, String player_of_match, String venue, int team1_runs, int team2_runs,int team1_wickets, int team2_wickets) {
        String msg = "Unsuccessful";

        try {
            getConnection();


              BasicDBObject doc = new BasicDBObject("title", "myEvents").
                      append("Id", Id).
                      append("Team1", Team1).
                      append("Team2", Team2).
                      append("Date", Date).
                      append("Time", Time).
                      append("City",city).
                      append("toss_win", toss_win).
                      append("toss_decision", toss_decision).
                      append("winner", winner).
                      append("win_by_runs", win_by_runs).
                      append("win_by_wickets", win_by_wickets).
                      append("player_of_match", player_of_match).
                      append("venue", venue).
                      append("team1_runs", team1_runs).
                      append("team2_runs", team2_runs).
                      append("team1_wickets", team1_wickets).
                      append("team2_wickets", team2_wickets);
              myEvents.insert(doc);
              msg = "Successful";
          }



            catch (Exception e) {
                msg =  "UnSuccessful";
            }

          //  return msg;


    }
/*******************************************************************/

    public static HashMap<String, ArrayList<Event>> selectEvent() {
        HashMap<String, ArrayList<Event>> events = null;

        try {

            getConnection();
            DBCursor cursor = myEvents.find();
            events = new HashMap<String, ArrayList<Event>>();
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();

                if (!events.containsKey(obj.getString("Id"))) {
                    ArrayList<Event> arr = new ArrayList<Event>();
                    events.put(obj.getString("Id"), arr);
                    System.out.println("Already present");
                }
                ArrayList<Event> listEvent = events.get(obj.getString("Id"));
                Event event = new Event(obj.getString("Id"), obj.getString("Team1"), obj.getString("Team2"),
                        obj.getString("Date"), obj.getString("City"),obj.getString("Time"), obj.getString("toss_win"), obj.getString("toss_decision"),
                        obj.getString("winner"), obj.getInt("win_by_runs"), obj.getInt("win_by_wickets"), obj.getString("player_of_match"), obj.getString("venue"), obj.getInt("team1_runs"),obj.getInt("team2_runs"),obj.getInt("team1_wickets"),obj.getInt("team2_wickets"));
                //add to review hashmap
                listEvent.add(event);
                System.out.println("ADDED");
            }
            return events;
        } catch (Exception e) {
            events = null;
            System.out.println("Error in catching DB");
            return events;
        }
    }

/*************************************************************************/
//Players
public static void insertPlayers(String playerId, String playerName, String teamName, String image, String jersey, int matches, int runs, int wickets, int catches, double BattingAvg, double BowlingAvg){

   String msg = "Unsuccessful";

    try {
        getConnection2();


          BasicDBObject doc = new BasicDBObject("title", "myPlayers").
                  append("playerId", playerId).
                  append("PlayerName", playerName).
                  append("teamName", teamName).
                  append("image", image).
                  append("jersey", jersey).
                  append("matches", matches).
                  append("runs", runs).
                  append("wickets", wickets).
                  append("catches", catches).
                  append("BattingAvg", BattingAvg).
                  append("BowlingAvg", BowlingAvg);
          myPlayers.insert(doc);
          msg = "Successful";
      }



        catch (Exception e) {
            msg =  "UnSuccessful";
        }

        // return msg;


}




    public static String insertReview(String productname, String username, String producttype, String productmaker,
                                      String reviewrating, String reviewdate, String reviewtext, String retailerpin,
                                      String price, String retailercity, String userAge, String userGender, String userOccupation) {
        try {
            getConnection1();
            BasicDBObject doc = new BasicDBObject("title", "myReviews").
                    append("userName", username).
                    append("productName", productname).
                    append("productType", producttype).
                    append("productMaker", productmaker).
                    append("reviewRating", Integer.parseInt(reviewrating)).
                    append("reviewDate", reviewdate).
                    append("reviewText", reviewtext).
                    append("retailerpin", retailerpin).
                    append("retailercity", retailercity).
                    append("userAge", userAge).
                    append("userGender", userGender).
                    append("userOccupation", userOccupation).
                    append("price", (int) Double.parseDouble(price));
            myReviews.insert(doc);
            return "Successful";
        } catch (Exception e) {
            return "UnSuccessful";
        }

    }

    public static HashMap<String, ArrayList<Review>> selectReview() {
        HashMap<String, ArrayList<Review>> reviews = null;

        try {

            getConnection1();
            DBCursor cursor = myReviews.find();
            reviews = new HashMap<String, ArrayList<Review>>();
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();

                if (!reviews.containsKey(obj.getString("productName"))) {
                    ArrayList<Review> arr = new ArrayList<Review>();
                    reviews.put(obj.getString("productName"), arr);
                }
                ArrayList<Review> listReview = reviews.get(obj.getString("productName"));
                Review review = new Review(obj.getString("productName"), obj.getString("userName"), obj.getString("productType"),
                        obj.getString("productMaker"), obj.getString("reviewRating"), obj.getString("reviewDate"), obj.getString("reviewText"),
                        obj.getString("retailerpin"), obj.getString("price"), obj.getString("retailercity"), obj.getString("userAge"), obj.getString("userGender"), obj.getString("userOccupation"));
                //add to review hashmap
                listReview.add(review);

            }
            return reviews;
        } catch (Exception e) {
            reviews = null;
            return reviews;
        }
    }




    /**************************************************************************************************/
    //Selct players for Charts

    public static ArrayList<Player> selectPlayersForChart() {

      // public static ArrayList<Review> selectReviewForChart() {
      //
      //
            ArrayList<Player> playerList = new ArrayList<Player>();
            try {

                getConnection2();

                BasicDBObject query = new BasicDBObject();
                query.put("runs", new BasicDBObject("$gt", 500));
                DBCursor dbCursor = myPlayers.find(query);
                while(dbCursor.hasNext()){
                    BasicDBObject obj = (BasicDBObject) dbCursor.next();
                    Player player = new Player(obj.getString("PlayerName"),obj.getInt("runs"));
                    playerList.add(player);
                }
                System.out.println("PLAYERLIST "+playerList);
                return playerList;


            }

            catch (

            Exception e) {
                playerList = null;

                return playerList;
            }

        }

//Event Details

        public static ArrayList<Event> selectEventsForChart(String Id) {

          // public static ArrayList<Review> selectReviewForChart() {
          //
          //
                ArrayList<Event> eventList = new ArrayList<Event>();
                try {

                    getConnection();

                    BasicDBObject query = new BasicDBObject();
                    query.put("Id", new BasicDBObject("$eq",Id));
                    DBCursor dbCursor = myEvents.find(query);
                    while(dbCursor.hasNext()){
                        BasicDBObject obj = (BasicDBObject) dbCursor.next();
                        Event event = new Event(obj.getString("Id"),obj.getString("Team1"),obj.getString("Team2"),obj.getInt("team1_runs"),obj.getInt("team2_runs"),obj.getInt("team1_wickets"),obj.getInt("team2_wickets"));
                        eventList.add(event);
                    }
                    System.out.println("EVENTLIST "+eventList);
                    return eventList;


                }

                catch (

                Exception e) {
                    eventList = null;

                    return eventList;
                }

            }







    public static ArrayList<BestRating> topProducts() {
        ArrayList<BestRating> Bestrate = new ArrayList<BestRating>();
        try {

            getConnection1();
            int retlimit = 5;
            DBObject sort = new BasicDBObject();
            sort.put("reviewRating", -1);
            DBCursor cursor = myReviews.find().limit(retlimit).sort(sort);
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();

                String prodcutnm = obj.get("productName").toString();
                String rating = obj.get("reviewRating").toString();
                BestRating best = new BestRating(prodcutnm, rating);
                Bestrate.add(best);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Bestrate;
    }

    public static ArrayList<Mostsoldzip> mostsoldZip() {
        ArrayList<Mostsoldzip> mostsoldzip = new ArrayList<Mostsoldzip>();
        try {
            System.out.println("top5");
            getConnection1();
            DBObject groupProducts = new BasicDBObject("_id", "$retailerpin");
            groupProducts.put("count", new BasicDBObject("$sum", 1));

           DBObject group = new BasicDBObject("$group", groupProducts);
            DBObject limit = new BasicDBObject();
            limit = new BasicDBObject("$limit", 5);

            DBObject sortFields = new BasicDBObject("count", -1);
            DBObject sort = new BasicDBObject("$sort", sortFields);
            AggregationOutput output = myReviews.aggregate(group, sort, limit);


            for (DBObject res : output.results()) {
                System.out.println(res);
                String zipcode = (res.get("_id")).toString();
                String count = (res.get("count")).toString();
                Mostsoldzip mostsldzip = new Mostsoldzip(zipcode, count);
                mostsoldzip.add(mostsldzip);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mostsoldzip;
    }

    public static ArrayList<Mostsold> mostsoldProducts() {
        ArrayList<Mostsold> mostsold = new ArrayList<Mostsold>();
        try {
            getConnection();
            DBObject groupProducts = new BasicDBObject("_id", "$productName");
            groupProducts.put("count", new BasicDBObject("$sum", 1));
            DBObject group = new BasicDBObject("$group", groupProducts);
            DBObject limit = new BasicDBObject();
            limit = new BasicDBObject("$limit", 5);

            DBObject sortFields = new BasicDBObject("count", -1);
            DBObject sort = new BasicDBObject("$sort", sortFields);
            AggregationOutput output = myReviews.aggregate(group, sort, limit);

            for (DBObject res : output.results()) {

                String productName = (res.get("_id")).toString();
                String count = (res.get("count")).toString();
                Mostsold mostsld = new Mostsold(productName, count);
                mostsold.add(mostsld);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mostsold;

    }
  
}
