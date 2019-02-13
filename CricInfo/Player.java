import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Player")


public class Player extends HttpServlet {
	private String id;
	private String name;
	private String image;
	private String jersey;
	private int runs;
	private int wickets;
	private int catches;
	private int matches;
	private String team;
	private double batavg;
	private double bowlavg;

	public Player(String name, String image, String jersey,String team,int matches, int runs,int wickets, int catches,double batavg,double bowlavg){
		this.name=name;
		this.jersey=jersey;
		this.image=image;
		this.matches= matches;
		this.runs=runs;
		this.wickets=wickets;
		this.catches=catches;
		this.team = team;
		this.batavg = batavg;
		this.bowlavg = bowlavg;

		//this.retailer = retailer;
	}
	public Player(String playerName, int runs) {
        this.name = playerName;
        this.runs = runs;

    }


	public Player() {

	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}





	public String getJersey() {
		return jersey;
	}

	public void setJersey(String jersey) {
		this.jersey = jersey;
	}

  public int getRuns(){
		return runs;

	}

	public void setRuns(int runs){
		this.runs= runs;
	}

	public int getMatches(){
		return matches;
	}

	public void setMatches(int matches){
		this.matches=matches;
	}

	public int getWickets(){
		return wickets;

	}

	public void setWickets(int wickets){
		this.wickets= wickets;
	}

	public int getCatches(){
		return catches;
	}

	public void setCatches(int catches){
		this.catches=catches;
	}

	public void setBatAvg(double batavg){
		this.batavg = batavg;
	}

	public double getBatAvg(){
		return batavg;
	}

	public void setBowlAvg(double bowlavg){
		this.bowlavg = bowlavg;
	}

	public double getBowlAvg(){
		return bowlavg;
	}

}
