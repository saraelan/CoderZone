import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Team")


public class Team extends HttpServlet {
	private String id;
	private String name;
	private String image;
//	private String jersey;
	private int won;
	private int lost;
	private int draw;
	private int matches;
	private String teamName;
	private double wonper;
	private double lostper;

	public Team(String teamName, String image,int matches, int won,int lost, int draw,double wonper,double lostper){
		this.teamName=teamName;
		// this.jersey=jersey;
		this.image=image;
		this.matches= matches;
		this.won=won;
		this.lost=lost;
		this.draw=draw;
		// this.team = team;
		this.wonper = wonper;
		this.lostper = lostper;

		//this.retailer = retailer;
	}


	public Team() {

	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}





	public String getName() {
		return teamName;
	}

	public void setName(String name) {
		this.teamName = name;
	}



	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// public String getRetailer() {
	// 	return retailer;
	// }
	//
	// public void setRetailer(String retailer) {
	// 	this.retailer = retailer;
	// }




  public int getWon(){
		return won;

	}

	public void setWon(int won){
		this.won= won;
	}

	public int getMatches(){
		return matches;
	}

	public void setMatches(int matches){
		this.matches=matches;
	}

	public int getLost(){
		return lost;

	}

	public void setLost(int lost){
		this.lost= lost;
	}

	public int getDraw(){
		return draw;
	}

	public void setDraw(int draw){
		this.draw=draw;
	}

	public void setWonPer(double wonper){
		this.wonper = wonper;
	}

	public double getWonPer(){
		return wonper;
	}

	public void setLostPer(double lostper){
		this.lostper = lostper;
	}

	public double getLostPer(){
		return lostper;
	}

}
