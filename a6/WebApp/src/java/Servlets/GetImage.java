/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import cs359db.db.PhotosDB;
import cs359db.model.Photo;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Geo
 */
@WebServlet(name = "GetImage", urlPatterns = {"/GetImage"})
public class GetImage extends HttpServlet {

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
        String id = request.getParameter("image");
        String metadata = request.getParameter("metadata");
        System.out.println(metadata);
        int Int_id = Integer.parseInt(id);

        // Use the appropriate type from the metadata
        response.setContentType("image/jpg");
        // Get the blob of the photo
        byte[] imgData = null;
        JSONObject list = new JSONObject();
        try {
            if ("false".equals(metadata)) {
                imgData = PhotosDB.getPhotoBlobWithID(Int_id);
            } else {

                try {

                    list.put("", PhotosDB.getPhotoMetadataWithID(Int_id).toString());
                } catch (ClassNotFoundException | JSONException ex) {
                    Logger.getLogger(UploadImage.class.getName()).log(Level.SEVERE, null, ex);
                }

                JSONArray inner = new JSONArray();
                inner.put(list);
                response.setContentType("application/json");

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UploadImage.class.getName()).log(Level.SEVERE, null, ex);
        }

        try ( // output with the help of outputStream
                OutputStream os = response.getOutputStream()) {
            os.write(imgData);
            os.flush();

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
