import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.Date.*;





public class Event implements Serializable {
    private String city;
    private String date;
    private String team1;
    private String team2;
    private String toss_win;
    private String toss_decision;
    private String winner;
    private int win_by_runs;
    private int win_by_wickets;
    private String player_of_match;
    private String venue;
    private int team1_runs;
    private int team2_runs;
    private int team1_wickets;
    private int team2_wickets;
    private String Id;
    private String time;

    public String getCity() {
        return city;
    }

    public String getTime(){
      return time;
    }

    public void setTime(String time){
      this.time = time;
    }

    public void setCity(String city) {
        this.city = city;;
    }

    public String getDate() {
        return date;
    }

    public String getId(){
      return Id;
    }

    public void setId(String Id){
      this.Id = Id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTossWin(){
      return toss_win;
    }

    public void setTossWin(String toss_win){
      this.toss_win= toss_win;
    }


    public String getTossDecision(){
      return toss_decision;

    }

    public void setTossDecision(String toss_decision){
      this.toss_decision = toss_decision;
    }

    public String getWinner(){
      return winner;
    }

    public void setWinner(String winner){
      this.winner = winner;
    }

    public int getWinByRuns(){
      return win_by_runs;
    }

    public void setWinByRuns(int win_by_runs){
      this.win_by_runs = win_by_runs;
    }

    public int getWinByWickets(){
      return win_by_wickets;
    }

    public void setWinByWickets(int win_by_wickets){
      this.win_by_wickets = win_by_wickets;
    }

    public String getPlayerOfMatch(){
      return player_of_match;
    }

    public void setPlayerOfMatch(String player_of_match){
      this.player_of_match = player_of_match;
    }

    public String getVenue(){
      return venue;
    }

    public void setVenue(String venue){
      this.venue = venue;
    }

    public int getTeam1Runs(){
      return team1_runs;
    }

    public void setTeam1Runs(int team1_runs){
      this.team1_runs = team1_runs;
    }

    public int getTeam2Runs(){
      return team2_runs;
    }

    public void setTeam2Runs(int team2_runs){
      this.team2_runs = team2_runs;
    }

    public int getTeam1Wickets(){
      return team1_wickets;
    }

    public void setTeam1Wickets(int team1_wickets){
      this.team1_wickets = team1_wickets;
    }

    public int getTeam2Wickets(){
      return team2_wickets;
    }

    public void setTeam2Wickets(int team2_wickets){
      this.team2_wickets = team2_wickets;
    }

    public Event(String Id, String Team1, String Team2, String Date, String Time, String city,
                                      String toss_win, String toss_decision, String winner, int win_by_runs,
                                      int win_by_wickets, String player_of_match, String venue, int team1_runs, int team2_runs,int team1_wickets, int team2_wickets) {
        this.Id = Id;
        this.team1 = Team1;
        this.team2 = Team2;
        this.date = Date;
        this.time = Time;
        this.toss_win = toss_win;
        this.toss_decision = toss_decision;
        this.winner = winner;
        this.win_by_runs = win_by_runs;
        this.win_by_wickets = win_by_wickets;
        this.player_of_match = player_of_match;
        this.venue = venue;
        this.team1_runs = team1_runs;
        this.team2_runs= team2_runs;
        this.team1_wickets = team1_wickets;
        this.team2_wickets = team2_wickets;
        this.city = city;

    }

    public Event(){}

      public Event(String Id,String team1,String team2, int team1_runs, int team2_runs, int team1_wickets, int team2_wickets){
        this.team1 = team1;
        this.team2 = team2;
        this.team1_runs = team1_runs;
        this.team2_runs = team2_runs;
        this.team1_wickets =  team1_wickets;
        this.team2_wickets = team2_wickets;
        this.Id = Id;
      }

    // this.reviewText = reviewText;
    // }


}
