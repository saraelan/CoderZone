import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/Utilities")

/*
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.

*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session;
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument,
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

		public void printHtml(String file) {
	        String result = HtmlToString(file);
	        //to print the right navigation in header of username cart and logout etc
	        if (file.equals("Header.html")) {
	            result = result + "<div id='menu' style='float: right;'><ul>";
	            if (session.getAttribute("username") != null) {
	                String username = session.getAttribute("username").toString();
	                username = Character.toUpperCase(username.charAt(0)) + username.substring(1);

	                String userType = session.getAttribute("userType").toString();
	                switch (userType) {
	                    case "Customer":
	                        result = result + "<li><a><span class='glyphicon'>Hello, " + username + "</span></a></li>"
	                                + "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
	                                + "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
	                        break;
	                    case "Admin":
											result = result + "<li><a href='AdminHome'><span class='glyphicon'>Inventory</span></a></li>"
													 + "<li><a><span class='glyphicon'>Hello, " + username + "</span></a></li>"
													 // +"<li><a href='DataVisualization'><span class='glyphicon'>Data Visualization</span></a></li>"
													 // +"<li><a href='DataAnalytics'><span class='glyphicon'>DataAnalytics</span></a></li>"
													 // +"<li><a href='Inventory'><span class='glyphicon'>Inventory</span></a></li>"
													 +"<li><a href='SalesReport'><span class='glyphicon'>SalesReport</span></a></li>"
													 + "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
	                        break;

	                }
	            } else
	                result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>" + "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
	            result = result + "<li><a href='Cart'><span class='glyphicon'>Cart(" + CartCount() + ")</span></a></li></ul></div></div><div id='page'>";
	            pw.print(result);
	        } else
	            pw.print(result);
	    }


	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		}
		catch (Exception e) {
		}
		return result;
	}

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}

	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/

	public String username() {
        if (session.getAttribute("username") != null)
            return session.getAttribute("username").toString();
        return null;
    }

    /*  usertype Function returns the usertype from the session variable.*/
    public String usertype() {
        if (session.getAttribute("usertype") != null)
            return session.getAttribute("usertype").toString();
        return null;
    }

    /*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
    public User getUser() {
        String usertype = usertype();
        HashMap<String, User> hm = new HashMap<String, User>();
        hm = MySqlDataStoreUtilities.selectUser();

        return hm.get(username());
    }

	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>();
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\CricInfo\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{

			}
			int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
					 size=size + 1;

			}
			return size;
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}


	public void storeProduct(String name,String type,String maker, String acc){
	if(!OrdersHashMap.orders.containsKey(username())){
		ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
		OrdersHashMap.orders.put(username(), arr);
	}
	ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
	HashMap<String,Ticket> allticket = new HashMap<String,Ticket> ();
		HashMap<String,Souvenir> allsouvenir = new HashMap<String,Souvenir> ();
		//Laptops
	if(type.equals("ticket")){
		Ticket ticket;
		try{
			allticket = MySqlDataStoreUtilities.getticket();
			}
			catch(Exception e){

			}
			ticket = allticket.get(name);
			OrderItem orderitem = new OrderItem(ticket.getName(), ticket.getPrice(), ticket.getImage());
			orderItems.add(orderitem);
	}
	//Phones
	if(type.equals("souvenir")){
	  Souvenir souvenir = null;
	  try{
	  allsouvenir = MySqlDataStoreUtilities.getsouvenir();
	  }
	  catch(Exception e){

	  }
	  souvenir = allsouvenir.get(name);
	  OrderItem orderitem = new OrderItem(souvenir.getName(), souvenir.getPrice(), souvenir.getImage());
	  orderItems.add(orderitem);
	}
	//Speakers

}





//delete product
public void removeItemFromCart(String itemName) {
			ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
			int index = 0;


			for (OrderItem oi : orderItems) {
					if (oi.getName().equals(itemName)) {
							break;
					} else index++;
			}
			orderItems.remove(index);
	}

			//OrdersHashMap.orders.values(name);


			public boolean removeProduct(String productId, String catalog) {
			        switch (catalog) {
			            case "souvenir":
			                SaxParserDataStore.souvenirmap.remove(productId);
											MySqlDataStoreUtilities.deleteproducts(productId);
			                return true;


			            case "ticket":

			                SaxParserDataStore.ticketmap.remove(productId);
											MySqlDataStoreUtilities.deleteproducts(productId);
			                return true;



			        }
			        return false;
			    }



	public void storePayment(int orderId,
													 String orderName, double orderPrice, String userAddress, String creditCardNo) {

			String username = (String) session.getAttribute("username");

			MySqlDataStoreUtilities.insertOrder(orderId, username, orderName, orderPrice, userAddress, creditCardNo);
	}

	public boolean removeOldOrder(int orderId, String orderName, String customerName) {
			return MySqlDataStoreUtilities.deleteOrder(orderId);
	}

	public void storeNewOrder(int orderId, String orderName, String customerName, double orderPrice, String userAddress, String creditCardNo) {
        MySqlDataStoreUtilities.insertOrder(orderId, customerName, orderName, orderPrice, userAddress, creditCardNo);

    }

		//Create Products
		public boolean createProduct(String id, String name, Double price, Double discount, String image, String type) {
        switch (type) {
            case "ticket":
                Ticket ticket= new Ticket();
                ticket.setId(id);
                ticket.setName(name);
                ticket.setPrice(price);
              //  fitness.setRetailer(manufacturer);
              //  ticket.setCondition(condition);
                ticket.setDiscount(discount);
                ticket.setImage(image);
                SaxParserDataStore.ticketmap.remove(id);
                SaxParserDataStore.ticketmap.put(id, ticket);
								MySqlDataStoreUtilities.addproducts(type,id,name,price,image,discount);
                return true;
            case "souvenir":

                Souvenir souvenir = new Souvenir();
                souvenir.setId(id);
                souvenir.setName(name);
                souvenir.setPrice(price);
                //smartWatch.setRetailer(manufacturer);
              //  souvenir.setCondition(condition);
                souvenir.setDiscount(discount);
                souvenir.setImage(image);
                SaxParserDataStore.souvenirmap.remove(id);
                SaxParserDataStore.souvenirmap.put(id, souvenir);
								MySqlDataStoreUtilities.addproducts(type,souvenir.getId(),souvenir.getName(),souvenir.getPrice(),souvenir.getImage(),souvenir.getDiscount());
                return true;

        }
        return false;
    }

//Create players

public boolean createPlayers(String id, String name, String image,String team, String jersey, int matches,int runs,int wickets, int catches) {

						Player player= new Player();
						player.setId(id);
						player.setName(name);
						player.setTeam(team);
					//  fitness.setRetailer(manufacturer);
					//  ticket.setCondition(condition);
						//player.setDiscount(Double.parseDouble(discount));
						player.setImage(image);
						player.setJersey(jersey);
						player.setMatches(matches);
						player.setRuns(runs);
						player.setWickets(wickets);
						player.setCatches(catches);
					double	batavg = (double)(player.getRuns()/(double)player.getMatches());
						double bowlavg = (double)(player.getWickets()/(double)player.getMatches());
						double roundOff_bat = (double) Math.round(batavg * 100) / 100;
						 double roundOff_bowl = (double) Math.round(bowlavg * 100) / 100;
						// double batavg = player.getRuns()/player.getRuns();
						// double bowlavg = player.getWickets()/player.getMatches();
						player.setBatAvg(roundOff_bat);
						player.setBowlAvg(roundOff_bowl);
						SaxParserDataStore.playermap.remove(id);
						SaxParserDataStore.playermap.put(id, player);
						MySqlDataStoreUtilities.addplayers(id, name, image,team, jersey, matches,runs,wickets, catches,roundOff_bat,roundOff_bowl);

						return true;

}


public void createTeam(String id, String name, String image,int matches,int won,int lost, int draw) {

						Team team= new Team();
						team.setId(id);
						team.setName(name);
						// team.setTeam(team);
					//  fitness.setRetailer(manufacturer);
					//  ticket.setCondition(condition);
						//team.setDiscount(Double.parseDouble(discount));
						team.setImage(image);

						team.setMatches(matches);
						team.setWon(won);
						team.setLost(lost);
						team.setDraw(draw);
						double winp = (double)(team.getWon()/(double)team.getMatches());
					 double lostp = (double)( team.getLost()/(double)team.getMatches());
						double winpercentage = (double) Math.round(winp * 100) / 100;
						double lostpercentage = (double) Math.round(lostp * 100) / 100;
						team.setWonPer(winpercentage);
						team.setLostPer(lostpercentage);
						SaxParserDataStore.teammap.remove(id);
						SaxParserDataStore.teammap.put(id, team);
						MySqlDataStoreUtilities.addteams( id,  name, image, matches, won,  lost,draw,  winpercentage, lostpercentage);

					//	return true;

}

public void updateTeam(String id, String name, String image,int matches,int won,int lost, int draw){
	Team team= new Team();
	team.setId(id);
	team.setName(name);
	// team.setTeam(team);
//  fitness.setRetailer(manufacturer);
//  ticket.setCondition(condition);
	//team.setDiscount(Double.parseDouble(discount));
	team.setImage(image);

	team.setMatches(matches);
	team.setWon(won);
	team.setLost(lost);
	team.setDraw(draw);
	double wonper = team.getWon()/team.getMatches();
	double lostper = team.getLost()/team.getMatches();
	double winp = (double)(team.getWon()/(double)team.getMatches());
 double lostp = (double)( team.getLost()/(double)team.getMatches());
	double winpercentage = (double) Math.round(winp * 100) / 100;
	double lostpercentage = (double) Math.round(lostp * 100) / 100;
	team.setWonPer(winpercentage);
	team.setLostPer(lostpercentage);
	SaxParserDataStore.teammap.remove(id);
	SaxParserDataStore.teammap.put(id, team);
	//MySqlDataStoreUtilities.addteams( id,  name, image, matches, won,  lost,draw,  winpercentage, lostpercentage);
	MySqlDataStoreUtilities.updateteam( id,  name, image, matches, won,  lost,draw,  winpercentage, lostpercentage);
}

//Store Review
		public void storeEvent(String Id, String Team1, String Team2, String Date, String Time, String city,
                                      String toss_win, String toss_decision, String winner, int win_by_runs,
                                      int win_by_wickets, String player_of_match, String venue, int team1_runs, int team2_runs,int team1_wickets, int team2_wickets) {
	         MongoDBDataStoreUtilities.insertEvent2(Id,Team1,Team2,Date,Time,city, toss_win,  toss_decision, winner,  win_by_runs, win_by_wickets,  player_of_match,  venue,  team1_runs, team2_runs, team1_wickets,  team2_wickets);


	            Event event = new Event(Id,Team1,Team2,Date,Time,city,toss_win,  toss_decision, winner,  win_by_runs, win_by_wickets,  player_of_match,  venue,  team1_runs, team2_runs, team1_wickets,  team2_wickets);
							SaxParserDataStore.eventmap.put(event.getId(),event);
	           // listEvent.add(event);

	            // add Reviews into database

	           // return "Successful";

	    }
			/*****************************************************************************************************************************/
			public String storeReview(String productname, String producttype, String productmaker, String reviewrating,
															String reviewdate, String reviewtext, String reatilerpin, String price, String city, String userAge, String userGender, String userOccupation) {
				String message = MongoDBDataStoreUtilities.insertReview(productname, username(), producttype, productmaker, reviewrating, reviewdate, reviewtext, reatilerpin, price, city, userAge, userGender, userOccupation);
				if (!message.equals("Successful")) {
						return "UnSuccessful";
				} else {
						HashMap<String, ArrayList<Review>> reviews = new HashMap<String, ArrayList<Review>>();
						try {
								reviews = MongoDBDataStoreUtilities.selectReview();
						} catch (Exception e) {

						}
						if (reviews == null) {
								reviews = new HashMap<String, ArrayList<Review>>();
						}
						// if there exist product review already add it into same list for productname or create a new record with product name

						if (!reviews.containsKey(productname)) {
								ArrayList<Review> arr = new ArrayList<Review>();
								reviews.put(productname, arr);
						}
						ArrayList<Review> listReview = reviews.get(productname);
						Review review = new Review(productname, username(), producttype, productmaker, reviewrating, reviewdate, reviewtext, reatilerpin, price, city, userAge, userGender, userOccupation);
						listReview.add(review);

						// add Reviews into database

						return "Successful";
				}
		}

		///////////////////////////////////////////////////////////////////////////////////////////////

		public boolean updateProduct(String id, String name, Double price, Double discount, String image, String type) {
        switch (type) {
            case "souvenir":
                Souvenir souvenir = new Souvenir();
                souvenir.setId(id);
                souvenir.setName(name);
                souvenir.setPrice(price);
              //  fitness.setRetailer(manufacturer);
              //  souvenir.setCondition(condition);
                souvenir.setDiscount(discount);
                souvenir.setImage(image);
                SaxParserDataStore.souvenirmap.remove(id);
                SaxParserDataStore.souvenirmap.put(id, souvenir);
								MySqlDataStoreUtilities.updateproducts(type,id,name,price,image,discount);

                return true;
            case "ticket":

                Ticket ticket = new Ticket();
                ticket.setId(id);
                ticket.setName(name);
                ticket.setPrice(price);
                //smartWatch.setRetailer(manufacturer);
              //  ticket.setCondition(condition);
                ticket.setDiscount(discount);
                ticket.setImage(image);
                SaxParserDataStore.ticketmap.remove(id);
                SaxParserDataStore.ticketmap.put(id, ticket);
								MySqlDataStoreUtilities.updateproducts(type,id,name,price,image,discount);
                return true;

        }
        return false;
    }

		public boolean updatePlayers(String id, String name, String image,String team, String jersey, int matches,int runs,int wickets, int catches ) {

//playerId, playerName,image,teamName,jersey,matches,runs,wickets,catches,roundOff_bat,roundOff_bowl
			Player player= new Player();
			player.setId(id);
			player.setName(name);
			player.setTeam(team);
		//  fitness.setRetailer(manufacturer);
		//  ticket.setCondition(condition);
			//player.setDiscount(Double.parseDouble(discount));
			player.setImage(image);
			player.setJersey(jersey);
			player.setMatches(matches);
			player.setRuns(runs);
			player.setWickets(wickets);
			player.setCatches(catches);
			double	batavg = (double)(player.getRuns()/(double)player.getMatches());
				double bowlavg = (double)(player.getWickets()/(double)player.getMatches());
				double roundOff_bat = (double) Math.round(batavg * 100) / 100;
				 double roundOff_bowl = (double) Math.round(bowlavg * 100) / 100;
				// double batavg = player.getRuns()/player.getRuns();
				// double bowlavg = player.getWickets()/player.getMatches();
				player.setBatAvg(roundOff_bat);
				player.setBowlAvg(roundOff_bowl);
			SaxParserDataStore.playermap.remove(id);
			SaxParserDataStore.playermap.put(id, player);
			MySqlDataStoreUtilities.updateplayers(id, name, image,team, jersey, matches,runs,wickets, catches,roundOff_bat,roundOff_bowl);

			return true;


				}
				//return false;
	//	}

		public boolean isContainsStr(String string) {
        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(string);
        return m.matches();
    }

		public boolean isItemExist(String itemCatalog, String itemName) {

        HashMap<String, Object> hm = new HashMap<String, Object>();

        switch (itemCatalog) {
            case "ticket":
                hm.putAll(SaxParserDataStore.ticketmap);
                break;
            case "souvenir":
                hm.putAll(SaxParserDataStore.souvenirmap);
                break;

        }
        return true;
    }


// 		public void removeOldOrder(int orderId, String orderName, String customerName) {
//         String TOMCAT_HOME = System.getProperty("catalina.home");
//         HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
//         ArrayList<OrderPayment> ListOrderPayment = new ArrayList<OrderPayment>();
//         //get the order from file
//         try {
//             FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME + "\\webapps\\WebStore\\PaymentDetails.txt"));
//             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//             orderPayments = (HashMap) objectInputStream.readObject();
//         } catch (Exception e) {
//
//         }
//         //get the exact order with same ordername and add it into cancel list to remove it later
//         for (OrderPayment oi : orderPayments.get(orderId)) {
//             if (oi.getOrderName().equals(orderName) && oi.getUserName().equals(customerName)) {
//                 ListOrderPayment.add(oi);
//                 //pw.print("<h4 style='color:red'>Your Order is Cancelled</h4>");
// //                        response.sendRedirect("SalesmanHome");
// //                        return;
//             }
//         }
//         //remove all the orders from hashmap that exist in cancel list
//         orderPayments.get(orderId).removeAll(ListOrderPayment);
//         if (orderPayments.get(orderId).size() == 0) {
//             orderPayments.remove(orderId);
//         }
//
//         //save the updated hashmap with removed order to the file
//         updateOrderFile(orderPayments);
//     }



		public boolean updateOrderFile(HashMap<Integer, ArrayList<OrderPayment>> orderPayments) {
        String TOMCAT_HOME = System.getProperty("catalina.home");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME + "\\webapps\\CricInfo\\PaymentDetails.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(orderPayments);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {

        }
        return true;
    }


		public void updateOrder(int orderId, String customerName,
                            String orderName, double orderPrice, String userAddress, String creditCardNo) {
        MySqlDataStoreUtilities.deleteOrder(orderId);
        MySqlDataStoreUtilities.insertOrder(orderId, customerName, orderName, orderPrice, userAddress, creditCardNo);
    }


	/* getConsoles Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, Souvenir> getsouvenir(){
			HashMap<String, Souvenir> hm = new HashMap<String, Souvenir>();
			hm.putAll(SaxParserDataStore.souvenirmap);
			return hm;
	}

	/* getphones Functions returns the  Hashmap with all phones in the store.*/

	public HashMap<String, Ticket> getticket(){
			HashMap<String,Ticket> hm = new HashMap<String, Ticket>();
			hm.putAll(SaxParserDataStore.ticketmap);
			return hm;
	}

	/* getspeakers Functions returns the Hashmap with all speaker in the store.*/


	/* getProducts Functions returns the Arraylist of consoles in the store.*/

	public ArrayList<String> getProductsticket(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Ticket> entry : getticket().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProducts Functions returns the Arraylist of phones in the store.*/

	public ArrayList<String> getProductssouvenir(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Souvenir> entry : getsouvenir().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProducts Functions returns the Arraylist of speakers in the store.*/



}
