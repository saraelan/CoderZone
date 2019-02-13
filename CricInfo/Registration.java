import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Registration")

public class Registration extends HttpServlet {
    private String error_msg;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayRegistration(request, response, pw, false);
    }

	/*   Username,Password, Usertype information are Obtained from HttpServletRequest variable and validates whether
		 the User Credential Already Exists or else User Details are Added to the Users HashMap */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String email = request.getParameter("email");
        String usertype = "customer";
        if (!utility.isLoggedin())
            usertype = request.getParameter("userType");

        //if password and repassword does not match show error message

        if (!password.equals(repassword)) {
            error_msg = "Passwords doesn't match!";
        } else {

            HashMap<String, User> hm = new HashMap<String, User>();

            try {
                hm = MySqlDataStoreUtilities.selectUser();
            } catch (Exception e) {

            }


            // if the user already exist show error that already exist
            if (hm.containsKey(username))
                error_msg = "Username already exist as " + usertype;
            else {
				/*create a user object and store details into hashmap
				store the user hashmap into file  */

                User user = new User(username, password, usertype,email);
                hm.put(username, user);
                if (MySqlDataStoreUtilities.insertUser(username, password, repassword, usertype,email)) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("login_msg", "Your " + usertype + " account has been created. Please login");
                    if (!utility.isLoggedin()) {
                        response.sendRedirect("Login");
                        return;
                    } else {
                        response.sendRedirect("Account");
                        return;
                    }
                }

            }
        }

        //display the error message
        if (utility.isLoggedin()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("login_msg", error_msg);
            response.sendRedirect("Account");
            return;
        }
        displayRegistration(request, response, pw, true);

    }

    /*  displayRegistration function displays the Registration page of New User */

    protected void displayRegistration(HttpServletRequest request,
                                      HttpServletResponse response, PrintWriter pw, boolean error)
           throws ServletException, IOException {
       Utilities utility = new Utilities(request, pw);
       utility.printHtml("Header.html");
       utility.printHtml("LeftNavigationBar.html");
       pw.print("<div class='post3' style='float: none; width: 45%'>");
       pw.print(
               "<div class='entry'>"
               + "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
       if (error)
           pw.print("<h4 style='color:red'>" + error_msg + "</h4>");
           // pw.print("register.html");
       pw.print("	<center><h2 style='font-size: 24px;' 'font-color:black;'  'text-align:cente>Register!!</h2></center>"+
                  "<form method='post' action = 'Registration'> "+ "&nbsp"+
               "<div class='login'>"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+

                     //"<h1 align='center'>User Name</h1>"+
                     "UserName : <input type='text' name='username' value='' placeholder='username' class='input' required><hr> </input>"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+

                   //"	<h1 align='center'>Email Address</h1>"+
                   "	Email-Id : <input type='text' name='email' placeholder='Email' value='' class='input' required><hr> </input>"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+



                       //"<h1 align='center'>Usertype</h1>"
                     "UserType : <select name='userType' class='input' id='inlineFormCustomSelect'>"+
                         "<option selected>Choose Role ...</option>"+
                         "<option value='Customer'>Customer</option>"+
                         "<option value='Admin'>Admin</option>"+
                       "</select><hr>"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+







                     //"<h1 align='center'>Password</h1>"+
                     "Password : <input type='password' name='password' placeholder='password' value=''class='input' required><hr></input>"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+


                       //"<h1 align='center'>Confirm Password<label></h1>"+
                       "Confirm Password : <input type='password' name='repassword' placeholder='repassword' value='' class='input' required><hr></input>"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+"&nbsp"+


                     "<button class='btn btn-primary btn-sm' type='submit'  name='ByUser' value='Create User'>Create User</button>" +


           "</form>"+
           "</div>"

   );
        utility.printHtml("Footer.html");
    }
}
