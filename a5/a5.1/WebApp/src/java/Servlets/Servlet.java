package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cs359db.db.UserDB;
import cs359db.model.BCrypt;
import static cs359db.model.BCrypt.hashpw;
import cs359db.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geo
 */
@WebServlet(urlPatterns = {"/Echo"})
public class Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String patternpass = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,10}";
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String[] r;
        r = body.split("&");
        String username, password, cpassword, email, fname, lname, date, gender, country, city, comments;
        username = password = cpassword = email = fname = lname = date = gender = country = city = comments = "";
        if (r.length == 11) {
            username = r[0].substring(r[0].lastIndexOf("=") + 1);
            password = r[1].substring(r[1].lastIndexOf("=") + 1);
            cpassword = r[2].substring(r[2].lastIndexOf("=") + 1);
            email = r[3].substring(r[3].lastIndexOf("=") + 1);
            fname = r[4].substring(r[4].lastIndexOf("=") + 1);
            lname = r[5].substring(r[5].lastIndexOf("=") + 1);
            date = r[6].substring(r[6].lastIndexOf("=") + 1);
            gender = r[7].substring(r[7].lastIndexOf("=") + 1);
            country = r[8].substring(r[8].lastIndexOf("=") + 1);
            city = r[9].substring(r[9].lastIndexOf("=") + 1);
            comments = r[10].substring(r[10].lastIndexOf("=") + 1);

        }

        try (PrintWriter out = response.getWriter()) {
            int year = parseInt(date.substring(0, date.indexOf("-")));
            if ("".equals(password) || "".equals(cpassword) || "".equals(email) || "".equals(fname) || "".equals(lname)
                    || "".equals(username) || "".equals(date) || "".equals(country) || "".equals(city)) {
                out.println("<div class=\"alert alert-danger\">\n"
                        + "  <strong>Please submit all * fields\n"
                        + "</div>");
            } else if (!password.equals(cpassword)) {

                out.println("<div class=\"alert alert-danger\">\n"
                        + "  <strong>Password and Password Confirm are not the same!\n"
                        + "</div>");

            } else if (username.length() < 8) {

                out.println("<div class=\"alert alert-danger\">\n"
                        + "<strong>Username must contain at least 8 characters!\n"
                        + "</div>");

            } else if (!email.matches(ePattern)) {
                out.println("<div class=\"alert alert-danger\">\n"
                        + "  <strong>Ιncorrect email!(example: abc@abc.ab)!\n"
                        + "</div>");

            } else if (!password.matches(patternpass)) {

                out.println("<div class=\"alert alert-danger\">\n"
                        + "  <strong>Password must contain at least a latin character a number and a symbol ‘#’, ‘$’, ‘%’, etc. \n"
                        + "</div>");

            } else if (fname.length() < 3 || fname.length() > 21 || !fname.matches("^[a-zA-Z ]+$")) {

                out.println("<div class=\"alert alert-danger\">\n"
                        + "  <strong>Name must be 3 to 20 characters and only in english\n"
                        + "</div>");

            } else if (lname.length() < 4 || lname.length() > 21 || !lname.matches("^[a-zA-Z ]+$")) {
                out.println("<div class=\"alert alert-danger\">\n"
                        + "  <strong>Last Name must be between 4 to 20 characters and only in english!\n"
                        + "</div>");

            } else if (city.length() < 2 || city.length() > 51) {
                out.println("<div class=\"alert alert-danger\">\n"
                        + "  <strong>City must be between 2 to 50 characters!\n"
                        + "</div>");

            } else if ((2016 - year) < 15) {
                out.println("<div class=\"alert alert-danger\">\n"
                        + "  <strong>Cannot sign-up below 15 years old \n"
                        + "</div>");
            } else {
                User user = new User(username, email, password, fname, lname, date, country, city
                );
                try {
                    if (UserDB.checkValidUserName(username) && UserDB.checkValidEmail(email)) {
                        UserDB.addUser(user);

                        String hashed = hashpw(password, BCrypt.gensalt());
                        Cookie c1 = new Cookie("userCookie", username);
                        Cookie c2 = new Cookie("passwCookie", hashed);
                        response.addCookie(c1);
                        response.addCookie(c2);
                        out.println("<div class=\"alert alert-success\">\n"
                                + " <strong>Success!</strong> Succesfully signed-up!\n"
                                + "</div>");
                        out.println("<div class=\"Form1\">");
                        out.println("</div>");
                        out.println("<meta http-equiv=REFRESH CONTENT=\"2; url = http://localhost:8084/WebApp/tiv.html\">");
                    } else {

                        out.println("<div class=\"alert alert-danger\">\n"
                                + "  <strong>Username already exists\n"
                                + "</div>");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
