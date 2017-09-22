
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Echo"})
public class NewUserServlet extends HttpServlet {

    private Client_Cart ncart;

    @Override
    public void init() {
        ncart = new Client_Cart();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        String[] x = body.split("&");

        String username = x[0];
        String email = x[1];
        String password = x[2];
        String password2 = x[3];
        String fName = x[4];
        String lName = x[5];
        String date = x[6];
        String gender = x[7];
        String country = x[8];
        String city = x[9];
        String info = x[10];

        String patternpass = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,10}";
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        try (PrintWriter out = response.getWriter()) {

            if ("Username=".equals(username) || "Email=".equals(email) || "Password=".equals(password) || "Verify=".equals(password2) || "First Name=".equals(fName)
                    || "Surname=".equals(lName) || "Date=".equals(date) || "Country=".equals(country) || "City=".equals(city)) {

                out.println("<h3>You must complete every *.</h3>");

            } else {

                String tmp = x[2].substring(x[2].lastIndexOf("=") + 1);         //password
                String tmp2 = x[3].substring(x[3].lastIndexOf("=") + 1);        //varify

                String tmp3 = x[6].substring(x[6].lastIndexOf("=") + 1);        //date
                int ntmp3 = Integer.parseInt((tmp3).substring(0, tmp3.indexOf("-")));

                String tmp4 = x[0].substring(x[0].lastIndexOf("=") + 1);        //username
                String tmp5 = x[4].substring(x[4].lastIndexOf("=") + 1);        //fname
                String tmp6 = x[5].substring(x[5].lastIndexOf("=") + 1);        //lname
                String tmp7 = x[9].substring(x[9].lastIndexOf("=") + 1);        //city
                String tmp9 = x[1].substring(x[1].lastIndexOf("=") + 1);        //email
                String tmp10 = x[8].substring(x[8].lastIndexOf("=") + 1);       //country
                String tmp11 = x[7].substring(x[7].lastIndexOf("=") + 1);       //gender
                String tmp12 = x[10].substring(x[10].lastIndexOf("=") + 1);     //info

                if (tmp4.length() < 8) {

                    out.println("<h3>Username must contain at least 8 characters!</h3>");

                } else if (!tmp9.matches(ePattern)) {

                    out.println("<h3>Email uncorrect!(example: abc@abc.ab)</h3>");

                } else if (!tmp.matches(patternpass)) {

                    out.println("<h3>Password attributes dont match!</h3>");

                } else if (!tmp.equals(tmp2)) {

                    out.println("<h3>Password verification failed!</h3>");

                } else if (tmp5.length() < 3 || tmp5.length() > 21 || !tmp5.matches("^[a-zA-Z ]+$")) {

                    out.println("<h3>First Name must be between 3 to 20 characters and only in english!</h3>");

                } else if (tmp6.length() < 4 || tmp6.length() > 21 || !tmp6.matches("^[a-zA-Z ]+$")) {

                    out.println("<h3>Surname must be between 4 to 20 characters and only in english!</h3>");

                } else if ((2016 - ntmp3) < 15) {

                    out.println("<h3>You must be above 15!</h3>");

                } else if (tmp7.length() < 2 || tmp7.length() > 51) {

                    out.println("<h3>City must be between 2 to 50 characters!</h3>");

                } else {

                    int flag = 1;

                    for (HashMap.Entry<String, Client> entry : Client_Cart.c_cart.entrySet()) {

                        if ((entry.getValue().getUname().equals(tmp4))) {

                            out.println("Username already exists");
                            flag = 0;

                        }

                        if (((entry.getValue().getEmail().equals(tmp9)))) {

                            out.println("Email already exists");
                            flag = 0;

                        }

                    }

                    if (flag == 1) {
                        out.println("<h3>Successful sign in.</h3>");

                        HttpSession session = request.getSession();
                        session.setAttribute("name", tmp4);

                        Client_Cart.username = tmp4;

                        Client nClient = new Client(tmp4, tmp9, tmp, tmp2, tmp5, tmp6, tmp3, tmp11, tmp10, tmp7, tmp12);
                        if (!ncart.add(nClient)) {

                            out.print("<h3>You are already to the list</h3>");

                        } else {
                            out.print("<h3>You added to the list</h3>");
                        }

                    }
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
