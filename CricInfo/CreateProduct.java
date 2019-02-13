import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CreateProduct")
//This servlet is only include function of updating product
public class CreateProduct extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        Utilities utility = new Utilities(request, pw);

        String productId = request.getParameter("productId");
        String name = request.getParameter("productName");
        Double price = Double.parseDouble(request.getParameter("price"));

        Double discount = Double.parseDouble(request.getParameter("discount"));
        String catalog = request.getParameter("productCatalog");
        String image = request.getParameter("image");

        if (utility.createProduct(productId, name, price,  discount, image, catalog)) {
          //  pw.print("alert(\"Update successfully!\")");
            response.sendRedirect("AdminHome");
        }

    }
}
