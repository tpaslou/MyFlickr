package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cs359db.db.UserDB;
import cs359db.model.BCrypt;
import static cs359db.model.BCrypt.hashpw;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/LoginServ"})
public class LoginServ extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //response.setContentType("text/html;charset=UTF-8");

            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            String[] r = body.split("&");
            String username = r[0].substring(r[0].lastIndexOf("=") + 1);
            String password = r[1].substring(r[1].lastIndexOf("=") + 1);
            //for (User user : UserCart.UserCart1.values()) {
            int x = 0;
            for (int i = 0; i < UserDB.getUsers().size(); i++) {

                if (UserDB.getUsers().get(i).getUserName().equals(username) && (UserDB.getUsers().get(i).getPassword().equals(password))) {
                    x = 1;

                }
            }
            try (PrintWriter out = response.getWriter()) {
                if (x == 1) {
                    out.println("<meta http-equiv=REFRESH CONTENT=\"1; url = http://localhost:8084/WebApp/tiv.html\">");

                    out.println("<div class=\"alert alert-success\">\n"
                            + " <strong>Success!</strong> Succesfully Signed-In!\n"
                            + "</div>");
                    
                    String hashed = hashpw(password, BCrypt.gensalt());
                    Cookie c1 = new Cookie("userCookie", username);
                    Cookie c2 = new Cookie("passwCookie", hashed);
                    response.addCookie(c1);
                    response.addCookie(c2);
                } else {
                    out.println("<div class=\"alert alert-danger\">\n"
                            + "  <strong>Make sure your Username and Password are correct\n"
                            + "</div>");
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServ.class.getName()).log(Level.SEVERE, null, ex);
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
