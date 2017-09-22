/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;
import cs359db.db.UserDB;
import cs359db.model.BCrypt;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geo
 */
public class ListServ extends HttpServlet {

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
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String[] r;
        r = body.split("&");
        String username = r[0].substring(r[0].lastIndexOf("=") + 1);
        String password = r[1].substring(r[1].lastIndexOf("=") + 1);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (username.equals("") && BCrypt.checkpw("", password)) {
                out.println("<div class=\"alert alert-danger\">\n"
                        + "  <strong>You have to Log-in or Sign-Up to gain access to this page\n"
                        + "</div>");
                out.println("<meta http-equiv=REFRESH CONTENT=\"1; url = http://localhost:8084/WebApp/index.html\">");
            } else {
                int counter = 1;

                out.println("<table class=\"table table-striped table-inverse\"><thead><tr><th>#</th><th>First Name</th> <th>Last Name</th><th>Username</th></th><th>E-mail</th></tr></thead><tbody>");
                try {
                    for (int i = 0; i < UserDB.getUsers().size(); i++){
                        
                        out.println("<tr>");
                        out.println("<th scope=\"row\">" + counter + "</th>");
                        out.println("<td>" + UserDB.getUsers().get(i).getUserName() + "</td>");
                        out.println("<td>" + UserDB.getUsers().get(i).getLastName() + "</td>");
                        out.println("<td>" + UserDB.getUsers().get(i).getUserName() + "</td>");
                        out.println("<td>" + UserDB.getUsers().get(i).getEmail() + "</td>");
                        username=UserDB.getUsers().get(i).getUserName();
                        out.println("<input type='hidden' value="+username+" id="+i+">");
                        out.println("<input type='hidden' value="+4+" id='number'>");
                        out.println("<td>" +"<input type=\"button\" value=\"ShowImages\" onclick=\"UserDetails("+i+");\"> </td>");
                        out.println("</tr>");
                        counter++;
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ListServ.class.getName()).log(Level.SEVERE, null, ex);
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