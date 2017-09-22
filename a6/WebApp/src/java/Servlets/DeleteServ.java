/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import cs359db.db.PhotosDB;
import cs359db.db.UserDB;
import cs359db.model.BCrypt;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geo
 */
@WebServlet(name = "DeleteServ", urlPatterns = {"/DeleteServ"})
public class DeleteServ extends HttpServlet {

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
        String[] r = body.split("&");
        String username = r[0].substring(r[0].lastIndexOf("=") + 1);
        String password = r[1].substring(r[1].lastIndexOf("=") + 1);

        response.setContentType("text/html;charset=UTF-8");

       /* List<Integer> p = PhotosDB.getPhotoIDs(0, username);
        System.out.println("Array size :" + p.size());
        for (int i = 0; i < p.size(); i++) {
            System.out.println(p.get(i));
            PhotosDB.deletePhoto((int) (p.get(i)));
        }

        /*for (int i = 12; i < PhotosDB.getPhotoIDs(i).size(); i++) {

            PhotosDB.deletePhoto(i);

        }*/
        for (int i = 0; i < UserDB.getUsers().size(); i++) {

            if (UserDB.getUsers().get(i).getUserName().equals(username) && BCrypt.checkpw(UserDB.getUsers().get(i).getPassword(), password)) {
                //System.out.println("ela");
                //UserDB.deleteUser(UserDB.getUsers().get(i).getUserName());

                List<Integer> p = PhotosDB.getPhotoIDs(0, username);
                System.out.println("Array size :" + p.size());
                for (int j = 0; j < p.size(); j++) {
                    System.out.println(p.get(j));
                    PhotosDB.deletePhoto((int) (p.get(j)));
                }

               
               UserDB.deleteUser(UserDB.getUsers().get(i));
                
                try (PrintWriter out = response.getWriter()) {
                    out.println("<div class=\"alert alert-success\">\n"
                            + " <strong>Success!</strong> Succesfully Deleted Account!\n"
                            + "</div>");
                    out.println("<meta http-equiv=REFRESH CONTENT=\"1; url = http://localhost:8084/WebApp/index.html\">");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteServ.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DeleteServ.class.getName()).log(Level.SEVERE, null, ex);
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
