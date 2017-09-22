package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cs359db.db.UserDB;
import cs359db.model.BCrypt;
import cs359db.model.User;
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
public class infoServ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String[] r ;
        r = body.split("&");
        String username = r[0].substring(r[0].lastIndexOf("=") + 1);
        String password = r[1].substring(r[1].lastIndexOf("=") + 1);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        if(username.equals("") && BCrypt.checkpw("", password)){out.println("<div class=\"alert alert-danger\">\n"
                                + "  <strong>You have to Log-in or Sign-Up to gain access to this page\n"
                                + "</div>");
        out.println("<meta http-equiv=REFRESH CONTENT=\"1; url = http://localhost:8084/WebApp/index.html\">");
        }else{
        
            for (User user : UserDB.getUsers()) {
                
                if (username.equals(user.getUserName()) && BCrypt.checkpw(user.getPassword(), password)) {
                    out.println("<div class=\"input-group input-group-lg\">");
                    out.println("<span class=\"input-group-addon\" id=\"sizing-addon1\"></span>");
                    out.println("<input id='username'  type=\"text\" class=\"form-control\" placeholder=\"Username\"  name='username' value=" + user.getUserName() + " aria-describedby=\"sizing-addon1\" pattern=\".{8,}\" title=\"More than 8 Characters\" required disabled>");
                    out.println("</div>");
                    out.println("<br>");

                    out.println("<div class=\"input-group input-group-lg\">");
                    out.println("<span class=\"input-group-addon\" id=\"sizing-addon1\"></span>");
                    out.println("<input id='password'  type=\"text\" class=\"form-control\" placeholder=\"Password\"  name='password' value=" + user.getPassword() + " aria-describedby=\"sizing-addon1\" pattern=\".{8,}\" title=\"More than 8 Characters\" required disabled>");
                    out.println("</div>");
                    out.println("<br>");

                    out.println("<div class=\"input-group input-group-lg\">");
                    out.println("<span class=\"input-group-addon\" id=\"sizing-addon1\"></span>");
                    out.println("<input id='username'  type=\"text\" class=\"form-control\" placeholder=\"Username\"  name='username' value=" + user.getUserName() + " aria-describedby=\"sizing-addon1\"  required disabled>");
                    out.println("</div>");
                    out.println("<br>");

                    out.println("<div class=\"input-group input-group-lg\">");
                    out.println("<span class=\"input-group-addon\" id=\"sizing-addon1\"></span>");
                    out.println("<input id='email'  type=\"text\" class=\"form-control\" placeholder=\"email\"  name='email' value=" + user.getEmail() + " aria-describedby=\"sizing-addon1\"  required disabled>");
                    out.println("</div>");
                    out.println("<br>");

                    out.println("<div class=\"input-group input-group-lg\">");
                    out.println("<span class=\"input-group-addon\" id=\"sizing-addon1\"></span>");
                    out.println("<input id='birthday'  type=\"date\" class=\"form-control\" placeholder=\"Date of Birth\" name='birthday' value=" + user.getBirthDate() + " aria-describedby=\"sizing-addon1\" disabled>");
                    out.println("</div>");
                    out.println("<br>");
                }
            }

        }
    }}

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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(infoServ.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(infoServ.class.getName()).log(Level.SEVERE, null, ex);
        }
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
