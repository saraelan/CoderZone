import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MySqlDataStoreUtilities {
    static Connection conn = null;

    public static void getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/csp584proj?useUnicode=true&characterEncoding=utf8", "root", "root");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTables(){
      try{
        getConnection();

        //Create a table for players
        String players = "Create table players (playerId varchar(255), playerName varchar(255), teamName(255), image varchar(255), jersey varchar(255), matches int, runs int, wickets int, catches int, BattingAvg double, BowlingAvg double)";
        PreparedStatement pst = conn.prepareStatement(players);
        // pst.setInt(1, orderId);
        pst.executeUpdate();

        // Create a table for Productdetails
        String productdetails= "Create table productdetails (productType varchar(255),Id varchar(255), productName varchar(255), productPrice double, productImage varchar(255), productDiscount double)";
        PreparedStatement pst1 = conn.prepareStatement(productdetails);
        // pst.setInt(1, orderId);
        pst1.executeUpdate();

        //Create a table for team
        String team = "Create table team (teamName varchar(255), matches int, won int, lost int, draw int, winpercentage double, lostpercentage double)";
        PreparedStatement pst2 = conn.prepareStatement(team);
        // pst.setInt(1, orderId);
        pst2.executeUpdate();

        // Create a table for users
        String user = "Create table user (username varchar(255), password varchar(255), repassword varchar(255),userType varchar(255), email varchar(255))";
        PreparedStatement pst3 = conn.prepareStatement(user);
        // pst.setInt(1, orderId);
        pst3.executeUpdate();

        //Create a table for Orders
        String orders = "Create table orders(orderId varchar(255), userName varchar(255),orderName varchar(255),orderPrice double, userAddress varchar(255),creditCardNo int, orderTime varchar(255))";
        PreparedStatement pst4 = conn.prepareStatement(orders);
        // pst.setInt(1, orderId);
        pst3.executeUpdate();


      }catch(Exception e){

      }
    }

    public static boolean deleteOrder(int orderId) {
        try {

            getConnection();
            String deleteOrderQuery = "Delete from orders where OrderId=?";
            PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
            pst.setInt(1, orderId);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static void insertOrder(int orderId, String userName, String orderName, double orderPrice, String userAddress, String creditCardNo) {
        try {


            Date current_date = new Date();

            SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            getConnection();

            String insertIntoCustomerOrderQuery = "insert into orders (orderID, userName, orderName, orderPrice, userAddress, creditCardNo, orderTime) VALUES (?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
            //set the parameter for each column and execute the prepared statement
            pst.setInt(1, orderId);
            pst.setString(2, userName);
            pst.setString(3, orderName);
            pst.setDouble(4, orderPrice);
            pst.setString(5, userAddress);
            pst.setString(6, creditCardNo);
            pst.setString(7, SimpleDateFormat.format(current_date.getTime()));
            pst.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder() {

        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();

        try {

            getConnection();
            //select the table
            String selectOrderQuery = "select * from orders";
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            ResultSet rs = pst.executeQuery();
            ArrayList<OrderPayment> orderList = new ArrayList<OrderPayment>();
            while (rs.next()) {
                if (!orderPayments.containsKey(rs.getInt("OrderId"))) {
                    ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
                    orderPayments.put(rs.getInt("orderId"), arr);
                }
                ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));
                System.out.println("data is" + rs.getInt("OrderId") + orderPayments.get(rs.getInt("OrderId")));

                //add to orderpayment hashmap
                OrderPayment order = new OrderPayment(rs.getInt("OrderId"), rs.getString("userName"), rs.getString("orderName"), rs.getDouble("orderPrice"), rs.getString("userAddress"), rs.getString("creditCardNo"));
                listOrderPayment.add(order);

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return orderPayments;
    }


    public static boolean insertUser(String username, String password, String rePassword, String userType, String email) {
        try {

            getConnection();
            String insertIntoCustomerRegisterQuery = "INSERT INTO user(username,password,repassword,usertype,email) "
                    + "VALUES (?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, rePassword);
            pst.setString(4, userType);
            pst.setString(5,email);
            pst.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public static HashMap<String, User> selectUser() {
        HashMap<String, User> hm = new HashMap<String, User>();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String selectCustomerQuery = "select * from user";
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
            while (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("usertype"),rs.getString("email"));
                hm.put(rs.getString("username"), user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return hm;
    }

/************************************************************************/
    //Products
    public static void Insertproducts()
{
	try{

		getConnection();
		String insertProductQurey = "INSERT INTO  productdetails(ProductType,Id,productName,productPrice,productImage,productDiscount)" +
		"VALUES (?,?,?,?,?,?);";
    //Accessories
		for(Map.Entry<String,Souvenir> entry : SaxParserDataStore.souvenirmap.entrySet())
		{
			String name = "souvenir";
	        Souvenir acc = entry.getValue();

			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,acc.getId());
			pst.setString(3,acc.getName());
			pst.setDouble(4,acc.getPrice());
			pst.setString(5,acc.getImage());
			// pst.setString(6,acc.getCondition());
			pst.setDouble(6,acc.getDiscount());

			pst.executeUpdate();


		}
    //Laptop
    for(Map.Entry<String,Ticket> entry : SaxParserDataStore.ticketmap.entrySet())
    {
          Ticket con = entry.getValue();
      String name = "ticket";



      PreparedStatement pst = conn.prepareStatement(insertProductQurey);
      pst.setString(1,name);
      pst.setString(2,con.getId());
      pst.setString(3,con.getName());
      pst.setDouble(4,con.getPrice());
      pst.setString(5,con.getImage());
    //\\  pst.setString(6,con.getRetailer());
      // pst.setString(6,con.getCondition());
      pst.setDouble(6,con.getDiscount());

      pst.executeUpdate();

    }




	}catch(Exception e)
	{
  		e.printStackTrace();
	}
}


public static void Insertplayers()
{
try{

getConnection();
String insertProductQurey = "INSERT INTO  players(playerId,playerName,teamName,image,jersey,matches,runs,wickets,catches,BattingAvg,BowlingAvg)" +
"VALUES (?,?,?,?,?,?,?,?,?,?,?);";

for(Map.Entry<String,Player> entry : SaxParserDataStore.playermap.entrySet())
{
  // /String name = "player";
      Player acc = entry.getValue();

  PreparedStatement pst = conn.prepareStatement(insertProductQurey);
  pst.setString(1,acc.getId());
  pst.setString(2,acc.getName());
  pst.setString(3,acc.getTeam());
  pst.setString(4,acc.getImage());
  pst.setString(5,acc.getJersey());
  // pst.setString(6,acc.getCondition());
  pst.setInt(6,acc.getMatches());
  pst.setInt(7,acc.getRuns());
  pst.setInt(8,acc.getWickets());
  pst.setInt(9,acc.getCatches());
  pst.setDouble(10,acc.getBatAvg());
  pst.setDouble(11,acc.getBowlAvg());
  MongoDBDataStoreUtilities.insertPlayers(acc.getId(),acc.getName(),acc.getTeam(),acc.getImage(),acc.getJersey(),acc.getMatches(),acc.getRuns(),acc.getWickets(),acc.getCatches(),
  acc.getBatAvg(),acc.getBowlAvg());

  pst.executeUpdate();


}


}catch(Exception e)
{
  e.printStackTrace();
}
}



////teams

public static void Insertteams()
{
try{

getConnection();
String insertProductQurey = "INSERT INTO  team(teamName, matches, won, lost, draw, winpercentage, lostpercentage, Id, image)" +
"VALUES (?,?,?,?,?,?,?,?,?);";

for(Map.Entry<String,Team> entry : SaxParserDataStore.teammap.entrySet())
{
  // /String name = "player";
      Team acc = entry.getValue();

  PreparedStatement pst = conn.prepareStatement(insertProductQurey);
  pst.setString(1,acc.getName());
  pst.setInt(2,acc.getMatches());
  pst.setInt(3,acc.getWon());
  pst.setInt(4,acc.getLost());
  pst.setInt(5,acc.getDraw());
  // pst.setString(6,acc.getCondition());
  pst.setDouble(6,acc.getWonPer());
  pst.setDouble(7,acc.getLostPer());
  pst.setString(8,acc.getId());
  pst.setString(9,acc.getImage());



  pst.executeUpdate();


}
}catch(Exception e)
{
  e.printStackTrace();
}
}

//Laptops
public static HashMap<String,Ticket> getticket()
{
	HashMap<String,Ticket> hm=new HashMap<String,Ticket>();
	try
	{
		getConnection();

		String selectticket="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectticket);
		pst.setString(1,"ticket");
		ResultSet rs = pst.executeQuery();

		while(rs.next())
		{	Ticket ticket = new Ticket(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), ticket);
				ticket.setId(rs.getString("Id"));
		//		System.out.println(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;
}


//products
public static HashMap<String,Product> getproduct()
{
	HashMap<String,Product> hm=new HashMap<String,Product>();
	try
	{
		getConnection();

		String selectProduct="select * from  Productdetails";
		PreparedStatement pst = conn.prepareStatement(selectProduct);
	//	pst.setString(1,"ticket");
		ResultSet rs = pst.executeQuery();

		while(rs.next())
		{	Product product = new Product(rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productType"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), product);
				product.setId(rs.getString("Id"));
		//		System.out.println(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;
}
//Done

public static HashMap<String,Souvenir> getsouvenir()
{
	HashMap<String,Souvenir> hm=new HashMap<String,Souvenir>();
	try
	{
		getConnection();

		String selectphone="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectphone);
		pst.setString(1,"souvenir");
		ResultSet rs = pst.executeQuery();

		while(rs.next())
		{	Souvenir souvenir = new Souvenir(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), souvenir);
				souvenir.setId(rs.getString("Id"));
		//		System.out.println(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;
}

public static HashMap<String,Player> getPlayers()
{
	HashMap<String,Player> hm=new HashMap<String,Player>();
	try
	{
		getConnection();

		String selectphone="select * from players";
		PreparedStatement pst = conn.prepareStatement(selectphone);

		ResultSet rs = pst.executeQuery();
    System.out.println("YES");
		while(rs.next())

		{
      System.out.println("NO"+rs.getString("playerName"));
      Player player = new Player(rs.getString("playerName"),rs.getString("image"),rs.getString("jersey"),rs.getString("teamName"),rs.getInt("matches"),rs.getInt("runs"),rs.getInt("wickets"),rs.getInt("catches"),rs.getDouble("BattingAvg"),rs.getDouble("BowlingAvg"));
        System.out.println("Name"+player.getName());
				hm.put(rs.getString("playerId"), player);
				player.setId(rs.getString("playerId"));
		//		System.out.println(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;
}


public static HashMap<String,Team> getTeams()
{
	HashMap<String,Team> hm=new HashMap<String,Team>();
	try
	{
		getConnection();

		String selectphone="select * from team";
		PreparedStatement pst = conn.prepareStatement(selectphone);

		ResultSet rs = pst.executeQuery();
    System.out.println("YES");
		while(rs.next())

		{
      // System.out.println("NO"+rs.getString("playerName"));
      Team team = new Team(rs.getString("teamName"),rs.getString("image"),rs.getInt("matches"),rs.getInt("won"),rs.getInt("lost"),rs.getInt("draw"),rs.getDouble("winpercentage"),rs.getDouble("lostpercentage"));
        // System.out.println("Name"+player.getName());
				hm.put(rs.getString("Id"), team);
				team.setId(rs.getString("Id"));
		//		System.out.println(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;
}


public static String addproducts(String producttype,String productId,String productName,double productPrice,String productImage,double productDiscount)
{
	String msg = "Product is added successfully";
	try{

		getConnection();
		String addProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productDiscount)" +
		"VALUES (?,?,?,?,?,?);";

			String name = producttype;

			PreparedStatement pst = conn.prepareStatement(addProductQurey);
      pst.setString(1,producttype);
			pst.setString(2,productId);
			pst.setString(3,productName);

			pst.setDouble(4,productPrice);
			pst.setString(5,productImage);
		//	pst.setString(6,productManufacturer);
			// pst.setString(6,productCondition);
			pst.setDouble(6,productDiscount);

			pst.executeUpdate();

	}
	catch(Exception e)
	{
		msg = "Erro while adding the product";
		e.printStackTrace();

	}
	return msg;
}
public static void updateproducts(String producttype,String productId,String productName,double productPrice,String productImage,double productDiscount)
{
    String msg = "Product is updated successfully";
	try{

		getConnection();
		String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productDiscount=?,productType=? where Id =?;" ;



			PreparedStatement pst = conn.prepareStatement(updateProductQurey);

			pst.setString(1,productName);
			pst.setDouble(2,productPrice);
			pst.setString(3,productImage);
			//pst.setString(4,productManufacturer);
			// pst.setString(4,productCondition);
			pst.setDouble(4,productDiscount);
      pst.setString(5,producttype);
			pst.setString(6,productId);
			pst.executeUpdate();



	}
	catch(Exception e)
	{
		msg = "Product cannot be updated";
		e.printStackTrace();

	}
// return msg;
}
public static String deleteproducts(String productId)
{   String msg = "Product is deleted successfully";
	try
	{

		getConnection();
		String deleteproductsQuery ="Delete from Productdetails where Id=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,productId);

		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Proudct cannot be deleted";
	}
	return msg;
}

//Add Players

public static void addplayers(String playerId, String name, String image, String team, String jersey, int matches, int runs, int wickets, int catches, double batavg, double bowlavg )
{
	String msg = "Player is added successfully";
	try{

		getConnection();
    	String addProductQurey = "INSERT INTO  players(playerId,playerName,teamName,image,jersey,matches,runs,wickets,catches,BattingAvg,BowlingAvg)" +
    "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
    //Accessories

        //  Player acc = entry.getValue();

      PreparedStatement pst = conn.prepareStatement(addProductQurey);
      pst.setString(1,playerId);
      pst.setString(2,name);
      pst.setString(3,team);
      pst.setString(4,image);
      pst.setString(5,jersey);
      // pst.setString(6,acc.getCondition());
      pst.setInt(6,matches);
      pst.setInt(7,runs);
      pst.setInt(8,wickets);
      pst.setInt(9,catches);
      pst.setDouble(10,batavg);
      pst.setDouble(11,bowlavg);


      pst.executeUpdate();


	}

	catch(Exception e)
	{
		msg = "Erro while adding the product";
		e.printStackTrace();

	}
//	return msg;
}

//Update players

public static String updateplayers(String Id,String playerName,String image,String teamName, String jersey,int matches,int runs, int wickets, int catches ,double batavg,double bowlavg)
{
    String msg = "Player is updated successfully";
	try{

		getConnection();
		String updateProductQurey = "UPDATE players SET playerName=?,teamName=?,image=?,jersey=?,matches=?,runs=?,wickets=?,catches=?,BattingAvg=?,BowlingAvg=? where playerId =?;" ;



			PreparedStatement pst = conn.prepareStatement(updateProductQurey);


			pst.setString(1,playerName);
			pst.setString(2,teamName);
      pst.setString(3,image);

			pst.setString(4,jersey);
			pst.setInt(5,matches);
    	pst.setInt(6,runs);
    	pst.setInt(7,wickets);
    	pst.setInt(8,catches);
    	pst.setDouble(9,batavg);
    	pst.setDouble(10,bowlavg);
      pst.setString(11,Id);
			pst.executeUpdate();

      // pst.executeUpdate();

	}
	catch(Exception e)
	{
		msg = "Product cannot be updated";
		e.printStackTrace();

	}
 return msg;
}


///Delete Players

public static String deleteplayers(String playerName)
{   String msg = "Player is deleted successfully";
	try
	{

		getConnection();
		String deleteproductsQuery ="delete from players where playerId=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,playerName);

		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Player cannot be deleted";
	}
	return msg;
}


///TEAMS

public static String addteams(String Id, String name, String image, int matches, int won, int lost, int draw, double wonper, double lostper )
{
	String msg = "Team is added successfully";
	try{

		getConnection();
    String insertProductQurey = "INSERT INTO  team(teamName, matches, won, lost, draw, winpercentage, lostpercentage, Id, image)" +
    "VALUES (?,?,?,?,?,?,?,?,?);";
    //Accessories
    for(Map.Entry<String,Team> entry : SaxParserDataStore.teammap.entrySet())
    {
      // /String name = "player";
          Team acc = entry.getValue();

      PreparedStatement pst = conn.prepareStatement(insertProductQurey);
      pst.setString(1,acc.getName());
      pst.setInt(2,acc.getMatches());
      pst.setInt(3,acc.getWon());
      pst.setInt(4,acc.getLost());
      pst.setInt(5,acc.getDraw());
      // pst.setString(6,acc.getCondition());
      pst.setDouble(6,acc.getWonPer());
      pst.setDouble(7,acc.getLostPer());
      pst.setString(8,acc.getId());
      pst.setString(9,acc.getImage());



      pst.executeUpdate();




	}

}catch(Exception e)
	{
		msg = "Error while adding the product";
		e.printStackTrace();

	}
	return msg;
}

//Update players

public static String updateteam(String Id, String name, String image, int matches, int won, int lost, int draw, double wonper, double lostper )
{
    String msg = "Team is updated successfully";
	try{

		getConnection();
		String updateProductQurey = "UPDATE team SET teamName=?,matches=?,won=?,lost=?,draw=?,winpercentage=?,lostpercentage=?,image=? where Id =?;" ;



			PreparedStatement pst = conn.prepareStatement(updateProductQurey);

      pst.setString(1,name);

      pst.setInt(2,matches);
      pst.setInt(3,won);
      pst.setInt(4,lost);
      pst.setInt(5,draw);
      // pst.setString(6,acc.getCondition());
      pst.setDouble(6,wonper);
      pst.setDouble(7,lostper);
      pst.setString(8,image);
      pst.setString(9,Id);
      //pst.setString(9,image);
      pst.executeUpdate();


	}
	catch(Exception e)
	{
		msg = "Product cannot be updated";
		e.printStackTrace();

	}
 return msg;
}


///Delete Players

public static String deleteteam(String Id)
{   String msg = "Team is deleted successfully";
	try
	{

		getConnection();
		String deleteproductsQuery ="Delete from team where Id=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,Id);

		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Proudct cannot be deleted";
	}
	return msg;
}


public static HashMap<String,Player> getData()
	{
		HashMap<String,Player> hm=new HashMap<String,Player>();
		try
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  players";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{	Player player = new Player(rs.getString("playerName"),rs.getString("image"),rs.getString("jersey"),rs.getString("teamName"),rs.getInt("matches"),rs.getInt("runs"),rs.getInt("wickets"),rs.getInt("catches"),rs.getDouble("BattingAvg"),rs.getDouble("BowlingAvg"));
				hm.put(rs.getString("playerId"), player);
			}
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return hm;
	}

  public static HashMap<String,Product> getProdData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  productdetails";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{	Product p = new Product(rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("ProductType"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), p);
			}
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return hm;
	}

//

public static HashMap<String, Product> selectOnSale() {
        HashMap<String, Product> hm = new HashMap<String, Product>();
        try {
            getConnection();

            String selectAcc = "select Id,productName,productPrice, count(productName) as inventory from productdetails where productCondition = 'new' group by productName";
            PreparedStatement pst = conn.prepareStatement(selectAcc);
          //  pst.setString(1, "1");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getString("productName"), rs.getDouble("productPrice"), Integer.parseInt(rs.getString("inventory")));
                hm.put(rs.getString("Id"), product);
                product.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, Product> selectRebate() {
        HashMap<String, Product> hm = new HashMap<String, Product>();
        try {
            getConnection();

            String selectAcc = "select * from Productdetails where productDiscount > 0";
            PreparedStatement pst = conn.prepareStatement(selectAcc);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getString("productName"), rs.getDouble("productPrice"), Double.parseDouble(rs.getString("productDiscount")));
                hm.put(rs.getString("Id"), product);
                product.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
        }
        return hm;
    }


    public static HashMap<String, OrderPayment> selectSaleAmount() {
        HashMap<String, OrderPayment> hm = new HashMap<String, OrderPayment>();
        try {
            getConnection();

            String selectAcc = "select DISTINCT(temp.orderName),temp.saleAmount,orders.orderPrice,temp.price as total from orders, (select orderName, count(orderName) as saleAmount, SUM(orderPrice) as price from orders group by orderName) as temp where orders.orderName = temp.orderName";
            PreparedStatement pst = conn.prepareStatement(selectAcc);
            ResultSet rs = pst.executeQuery();

            int i = 0;
            while (rs.next()) {
                OrderPayment orderPayment = new OrderPayment(rs.getString("orderName"), rs.getDouble("orderPrice"), rs.getInt("saleAmount"),rs.getDouble("total"));
                i++;
                hm.put(String.valueOf(i), orderPayment);
                //orderPayment.setOrderId(Integer.parseInt(rs.getString("Id")));
            }
        } catch (Exception e) {
        }
        return hm;
    }
    public static HashMap<String, Product> selectInventory() {
      HashMap<String, Product> hm = new HashMap<String, Product>();
      try {
          getConnection();

          String selectAcc = "select Id,productName,productPrice, count(productName) as inventory from productdetails group by productName";
          PreparedStatement pst = conn.prepareStatement(selectAcc);
          ResultSet rs = pst.executeQuery();

          while (rs.next()) {
              Product product = new Product(rs.getString("productName"), rs.getDouble("productPrice"), rs.getInt("inventory"));
              hm.put(rs.getString("Id"), product);
              product.setId(rs.getString("Id"));

          }
      } catch (Exception e) {

      }
      return hm;
  }

  public static HashMap<String, OrderPayment> selectDailyTransaction() {
          HashMap<String, OrderPayment> hm = new HashMap<String, OrderPayment>();
          try {
              getConnection();

              String selectAcc = "SELECT count(orderTime) as soldAmount, orderTime from orders group by orderTime";
              PreparedStatement pst = conn.prepareStatement(selectAcc);
              ResultSet rs = pst.executeQuery();

              int i = 0;
              while (rs.next()) {
                  OrderPayment orderPayment = new OrderPayment(rs.getInt("soldAmount"), rs.getDate("orderTime"));
                  i++;
                  hm.put(String.valueOf(i), orderPayment);
                  //orderPayment.setId(rs.getString("Id"));
              }
          } catch (Exception e) {
          }
          return hm;
      }

      public static ArrayList<OrderPayment> selectDailyTransactionForChart() {
          ArrayList<OrderPayment> orderPaymentArrayList = new ArrayList<OrderPayment>();
          try {
              getConnection();

              String selectAcc = "SELECT count(orderTime) as soldAmount, orderTime from orders group by orderTime";
              PreparedStatement pst = conn.prepareStatement(selectAcc);
              ResultSet rs = pst.executeQuery();

              while (rs.next()) {
                  OrderPayment orderPayment = new OrderPayment(rs.getInt("soldAmount"), rs.getDate("orderTime"));
                  orderPaymentArrayList.add(orderPayment);
              }
          } catch (Exception e) {
          }
          return orderPaymentArrayList;
      }



}
