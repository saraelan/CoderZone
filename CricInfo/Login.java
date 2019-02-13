import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")

public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

		/* User Information(username,password,usertype) is obtained from HttpServletRequest,
		Based on the Type of user(customer,retailer,manager) respective hashmap is called and the username and
		password are validated and added to session variable and display Login Function is called */

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        HashMap<String, User> hm = new HashMap<String, User>();
        //user details are validated using a file
        //if the file contains username and password user entered user will be directed to home page
        //else error message will be shown
        try {
            hm = MySqlDataStoreUtilities.selectUser();
        } catch (Exception e) {

        }
        User user = hm.get(username);
        if (user != null) {
            String user_password = user.getPassword();
            if (password.equals(user_password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                session.setAttribute("userType", userType);
                if (userType.equalsIgnoreCase("Customer")) {
                    response.sendRedirect("Home");
                    return;
                } else if (userType.equalsIgnoreCase("Admin")) {
                    response.sendRedirect("AdminHome");
                    return;

                }
            }
        }

        displayLogin(request, response, pw, true);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayLogin(request, response, pw, false);
    }


    /*  Login Screen is Displayed, Registered User specifies credentials and logins into the Game Speed Application. */
    protected void displayLogin(HttpServletRequest request,
                                HttpServletResponse response, PrintWriter pw, boolean error)
            throws ServletException, IOException {

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div class='post2' style='float: none; width: 25%' >");
        pw.print("<center><h2 style='font-size: 24px;' 'font-color:black;'  'text-align:center>Please Login !!</h2></center>"

                + "<div class='entry'>"
                + "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
        if (error)
            pw.print("<h4 style='color:red'>Please check your username, password and user type!</h4>");
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login_msg") != null) {
            pw.print("<h4 style='color:red'>" + session.getAttribute("login_msg") + "</h4>");
            session.removeAttribute("login_msg");
        }
        pw.print("<form method='post' action='Login'>"+
		"<div class='login'>"+

   " Username : <input type='text' placeholder='Username' name='username' value='' class='input' required><hr>  </input>"+
  "Password : <input type='password' placeholder='password' name='password' value='' class='input' required><hr>  </input>"+
   "User Type : <select name='userType' class='input'><option value='Customer' selected>Customer</option><option value='Admin'>Admin</option></select><hr>"+

                 "<button class='btn btn-primary btn-sm' type='submit'>Sign in</button>"+

                "<strong><a class='' href='Registration' style='float: right;height: 20px margin: 20px;'>New User? Register here!</a></strong>"+
               "<div class='shadow'></div>"+

"</form></div></div></div></div>");



        utility.printHtml("Footer.html");
    }
}
