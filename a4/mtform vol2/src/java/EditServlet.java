
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Echo5", urlPatterns = {"/Echo5"})
public class EditServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        String[] x = body.split("&");

        String password = x[0];
        String password2 = x[1];
        String fName = x[2];
        String lName = x[3];
        String date = x[4];
        String gender = x[5];
        String country = x[6];
        String city = x[7];
        String info = x[8];

        String patternpass = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,10}";

        try (PrintWriter out = response.getWriter()) {

            if ("Password=".equals(password) || "Verify=".equals(password2) || "First Name=".equals(fName)
                    || "Surname=".equals(lName) || "Date=".equals(date) || "Country=".equals(country) || "City=".equals(city)) {

                out.println("<h3>You must complete every *.</h3>");

            } else {

                String tmp = x[0].substring(x[0].lastIndexOf("=") + 1);     //password
                String tmp2 = x[1].substring(x[1].lastIndexOf("=") + 1);    //varify

                String tmp3 = x[4].substring(x[4].lastIndexOf("=") + 1);    //date
                int ntmp3 = Integer.parseInt((tmp3).substring(0, tmp3.indexOf("-")));

                String tmp5 = x[2].substring(x[2].lastIndexOf("=") + 1);    //fname
                String tmp6 = x[3].substring(x[3].lastIndexOf("=") + 1);    //lname
                String tmp7 = x[7].substring(x[7].lastIndexOf("=") + 1);    //city

                String tmp10 = x[6].substring(x[6].lastIndexOf("=") + 1);   //country
                String tmp11 = x[5].substring(x[5].lastIndexOf("=") + 1);   //gender
                String tmp12 = x[8].substring(x[8].lastIndexOf("=") + 1);   //info

                if (!tmp.matches(patternpass)) {

                    out.println("<h3>Password attributes dont match!</h3>");

                } else if (!tmp.equals(tmp2)) {

                    out.println("<h3>Password verification failed!</h3>");

                } else if (tmp5.length() < 3 || tmp5.length() > 21) {

                    out.println("<h2>First Name must be between 3 to 20 characters!</h3>");

                } else if (tmp6.length() < 4 || tmp6.length() > 21) {

                    out.println("<h3>Surname must be between 4 to 20 characters!</h3>");

                } else if ((2016 - ntmp3) < 15) {

                    out.println("<h3>You must be above 15!</h3>");

                } else if (tmp7.length() < 2 || tmp7.length() > 51) {

                    out.println("<h3>City must be between 2 to 50 characters!</h3>");

                } else {

                    out.println("<h3>Successful edit.</h3>");

                    String emailtmp = Client_Cart.c_cart.get(Client_Cart.username).getEmail();

                    Client nClient = new Client(Client_Cart.username, emailtmp, tmp, tmp2, tmp5, tmp6, tmp3, tmp11, tmp10, tmp7, tmp12);

                    Client_Cart.c_cart.remove(Client_Cart.username);

                    Client_Cart.c_cart.put(Client_Cart.username, nClient);

                }
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
