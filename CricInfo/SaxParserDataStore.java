
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API.
SAX was the first widely adopted API for XML in Java, and is a �de facto� standard.
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java.

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.Date.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure.
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
    Ticket ticket;
    Souvenir souvenir;
    Player player;
    Team team;
    Event event;
    static HashMap<String,Ticket> ticketmap;
    static HashMap<String,Souvenir> souvenirmap;
      static HashMap<String,Player> playermap;
      static HashMap<String,Team> teammap;
      static HashMap<String,Event> eventmap;

    String consoleXmlFileName;
    String elementValueRead;
	  String currentElement="";

    public SaxParserDataStore()
	{
	}

	public SaxParserDataStore(String consoleXmlFileName) {
    this.consoleXmlFileName = consoleXmlFileName;
    ticketmap = new HashMap<String, Ticket>();
	souvenirmap=new  HashMap<String, Souvenir>();
  playermap=new  HashMap<String, Player>();
  teammap = new HashMap<String,Team>();
  eventmap = new HashMap<String,Event>();
	parseDocument();

    }


    private void parseDocument()
	{

    try {
       File inputFile = new File(consoleXmlFileName);
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
       Document doc = dBuilder.parse(inputFile);
       doc.getDocumentElement().normalize();
       System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
       NodeList nList_ticket = doc.getElementsByTagName("ticket");
       System.out.println("----------------------------");

       for (int temp = 0; temp < nList_ticket.getLength(); temp++) {
          Node nNode = nList_ticket.item(temp);
          System.out.println("\nCurrent Element :" + nNode.getNodeName());

          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
             Element eElement = (Element) nNode;
             ticket = new Ticket();
             ticket.setId(eElement.getAttribute("id"));
             ticket.setImage(eElement.getElementsByTagName("image").item(0).getTextContent());
             ticket.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
             ticket.setPrice(Double.parseDouble(eElement.getElementsByTagName("price").item(0).getTextContent()));
             ticket.setDiscount(Double.parseDouble(eElement.getElementsByTagName("discount").item(0).getTextContent()));
             // laptop.setCondition(eElement.getElementsByTagName("condition").item(0).getTextContent());
          }
          ticketmap.put(ticket.getId(),ticket);
       }

       //phones

       NodeList nList_souvenir = doc.getElementsByTagName("souvenir");
       System.out.println("----------------------------");

       for (int temp = 0; temp < nList_souvenir.getLength(); temp++) {
          Node nNode = nList_souvenir.item(temp);
          System.out.println("\nCurrent Element :" + nNode.getNodeName());

          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
             Element eElement = (Element) nNode;
             souvenir = new Souvenir();
             souvenir.setId(eElement.getAttribute("id"));
             souvenir.setImage(eElement.getElementsByTagName("image").item(0).getTextContent());
             souvenir.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
             souvenir.setPrice(Double.parseDouble(eElement.getElementsByTagName("price").item(0).getTextContent()));
             souvenir.setDiscount(Double.parseDouble(eElement.getElementsByTagName("discount").item(0).getTextContent()));
             // phone.setCondition(eElement.getElementsByTagName("condition").item(0).getTextContent());
          }
          souvenirmap.put(souvenir.getId(),souvenir);
       }

       //Player List
              NodeList nList_player = doc.getElementsByTagName("player");
              System.out.println("----------------------------");

              for (int temp = 0; temp < nList_player.getLength(); temp++) {
                 Node nNode = nList_player.item(temp);
                 System.out.println("\nCurrent Element :" + nNode.getNodeName());

                 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    player = new Player();
                    player.setId(eElement.getAttribute("id"));
                    player.setImage(eElement.getElementsByTagName("image").item(0).getTextContent());
                    player.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    player.setTeam(eElement.getElementsByTagName("team").item(0).getTextContent());
                    player.setJersey(eElement.getElementsByTagName("jersey").item(0).getTextContent());
                    player.setMatches(Integer.parseInt(eElement.getElementsByTagName("matches").item(0).getTextContent()));
                    player.setRuns(Integer.parseInt(eElement.getElementsByTagName("runs").item(0).getTextContent()));
                    player.setWickets(Integer.parseInt(eElement.getElementsByTagName("wickets").item(0).getTextContent()));
                    player.setCatches(Integer.parseInt(eElement.getElementsByTagName("catches").item(0).getTextContent()));
                    int runs = player.getRuns();
                    int matches = player.getMatches();
                    int wickets = player.getWickets();
                    Double bat = (double)(runs/(double)matches);
                    Double bowl = (double)(wickets/(double)matches);
                    double roundOff_bat = (double) Math.round(bat * 100) / 100;
                    double roundOff_bowl = (double) Math.round(bowl * 100) / 100;
                    player.setBatAvg(roundOff_bat);
                    player.setBowlAvg(roundOff_bowl);
                    // phone.setCondition(eElement.getElementsByTagName("condition").item(0).getTextContent());
                 }
                playermap.put(player.getId(),player);
              }



              /// teams
              NodeList nList_team = doc.getElementsByTagName("group");
              System.out.println("----------------------------");

              for (int temp = 0; temp < nList_team.getLength(); temp++) {
                 Node nNode = nList_team.item(temp);
                 System.out.println("\nCurrent Element :" + nNode.getNodeName());

                 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                   System.out.println("----------------------------");
                    Element eElement = (Element) nNode;
                    team = new Team();
                    team.setId(eElement.getAttribute("id"));
                    // System.out.println("----------------------------");
                    // System.out.println("\nCurrent Element :" + team.getId());
                    team.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    team.setImage(eElement.getElementsByTagName("image").item(0).getTextContent());
                    team.setMatches(Integer.parseInt(eElement.getElementsByTagName("matches").item(0).getTextContent()));
                    team.setWon(Integer.parseInt(eElement.getElementsByTagName("won").item(0).getTextContent()));
                    team.setLost(Integer.parseInt(eElement.getElementsByTagName("lost").item(0).getTextContent()));
                    team.setDraw(Integer.parseInt(eElement.getElementsByTagName("draw").item(0).getTextContent()));
                    int won = team.getWon();
                    int matches = team.getMatches();
                    int lost = team.getLost();

                    Double won_per = (double)(won/(double)matches);
                    Double lost_per = (double)(lost/(double)matches);
                    double roundOff_won = (double) Math.round(won_per * 100) / 100;
                    double roundOff_lost = (double) Math.round(lost_per * 100) / 100;
                    team.setWonPer(roundOff_won);
                    team.setLostPer(roundOff_lost);
                    // phone.setCondition(eElement.getElementsByTagName("condition").item(0).getTextContent());
                 }
                 teammap.put(team.getId(),team);
              }

              //Events

              NodeList nList_event = doc.getElementsByTagName("events");
              System.out.println("----------------------------");

              for (int temp = 0; temp < nList_event.getLength(); temp++) {
                 Node nNode = nList_event.item(temp);
                 System.out.println("\nCurrent Element :" + nNode.getNodeName());

                 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                   System.out.println("----------------------------");
                    Element eElement = (Element) nNode;
                    event = new Event();
                    event.setId(eElement.getAttribute("id"));
                    // System.out.println("----------------------------");
                    // System.out.println("\nCurrent Element :" + team.getId());
                    event.setDate(eElement.getElementsByTagName("date").item(0).getTextContent());
                    event.setCity(eElement.getElementsByTagName("city").item(0).getTextContent());
                    event.setTime(eElement.getElementsByTagName("time").item(0).getTextContent());
                    event.setTeam1(eElement.getElementsByTagName("team1").item(0).getTextContent());
                    event.setTeam2(eElement.getElementsByTagName("team2").item(0).getTextContent());
                    event.setTossWin(eElement.getElementsByTagName("toss_win").item(0).getTextContent());
                    event.setTossDecision(eElement.getElementsByTagName("toss_decision").item(0).getTextContent());
                    event.setWinner(eElement.getElementsByTagName("winner").item(0).getTextContent());
                    event.setWinByRuns(Integer.parseInt(eElement.getElementsByTagName("win_by_runs").item(0).getTextContent()));
                    event.setWinByWickets(Integer.parseInt(eElement.getElementsByTagName("win_by_wickets").item(0).getTextContent()));
                    event.setPlayerOfMatch(eElement.getElementsByTagName("player_of_match").item(0).getTextContent());
                    event.setVenue(eElement.getElementsByTagName("venue").item(0).getTextContent());
                    event.setTeam1Runs(Integer.parseInt(eElement.getElementsByTagName("team1_runs").item(0).getTextContent()));
                    event.setTeam2Runs(Integer.parseInt(eElement.getElementsByTagName("team2_runs").item(0).getTextContent()));
                    event.setTeam1Wickets(Integer.parseInt(eElement.getElementsByTagName("team1_wickets").item(0).getTextContent()));
                    event.setTeam2Wickets(Integer.parseInt(eElement.getElementsByTagName("team2_wickets").item(0).getTextContent()));

                    // MongoDBDataStoreUtilities.insertEvent(event.getId(),event.getTeam1(),event.getTeam2(),event.getDate(),event.getTime(),
                    // event.getTossWin(),event.getTossDecision(),event.getWinner(),event.getWinByRuns(),event.getWinByWickets(),event.getPlayerOfMatch(),
                    // event.getVenue(),event.getTeam1Runs(),event.getTeam2Runs(),event.getTeam1Wickets(),event.getTeam2Wickets());



                    // phone.setCondition(eElement.getElementsByTagName("condition").item(0).getTextContent());
                 }
                 eventmap.put(event.getId(),event);
              }


    } catch (Exception e) {
       e.printStackTrace();
    }



	}



////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document.
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////




    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////

//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\CricInfo\\ProductCatalog.xml");
    }
}
